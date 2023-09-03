package main.mona_fp

import java.nio.file.{Files, Path, Paths}
import scala.annotation.tailrec
import scala.jdk.CollectionConverters._
import scala.collection.immutable.ArraySeq
import scala.util.Random

trait Image {
  val bytes: ArraySeq[Byte]
  val width: Int
  val height: Int

  def score(scoringFunction: (Int, Int) => Int, perfectImage: Image): ScoredImage = {
    val score = bytes.zipWithIndex.map { case (byte, idx) =>
      val perfectByte = perfectImage.bytes(idx)
      scoringFunction(byte, perfectByte)
    }.sum
    ScoredImage(bytes, width, height, score)
  }

  def drawRectangle(x: Int, y: Int, w: Int, h: Int, withValue: Byte): Image = {
    val xIndices = Range(x, x + w)
    val yIndices = Range(y, y + h)
    val indexes = xIndices.flatMap(x => yIndices.map(y => (x, y)))
    val result = indexes.foldLeft(bytes) { (arr, idx) =>
      arr.updated(translateWH(idx), withValue)
    }
    BaseImage(result, width, height)
  }

  private def translateWH(idx: (Int, Int)): Int = {
    idx._2 * width + idx._1
  }

  def writeToFile(to: Path): Path = {
    Files.write(to, bytes.toArray)
  }
}

case class ScoredImage(bytes: ArraySeq[Byte], width: Int, height: Int, score: Int) extends Image

case class BaseImage(bytes: ArraySeq[Byte], width: Int, height: Int) extends Image

object BaseImage {
  def empty(width: Int, height: Int): BaseImage = {
    BaseImage(ArraySeq.fill(width * height)(0), width, height)
  }
}

object Config {
  val W = 256
  val H = 382
  val BEST_NUM = 2
  val SPECIMEN_NUM = 300
}

object BaseStage {
  def empty(num: Int, image: Image) = {
    BaseStage(Seq.fill(num)(image))
  }
}

case class BaseStage(images: Seq[Image]) {

  def mutate(random: Random): BaseStage = {
    val mutatedImages = images.map { image =>
      val x = random.nextInt(image.width)
      val y = random.nextInt(image.height)
      val width = random.nextInt(image.width - x + 1)
      val height = random.nextInt(image.height - y + 1)
      val randomValue = random.nextInt(256)
      image.drawRectangle(x, y, width, height, randomValue.toByte)
    }
    BaseStage(mutatedImages)
  }

  def score(scoringFunction: (Int, Int) => Int, perfectImage: Image): ScoredStage =
    ScoredStage(images.map(image => image.score(scoringFunction, perfectImage)))
}

case class ScoredStage(images: Seq[ScoredImage]) {
  def generateBestStage(bestNum: Int): BaseStage = {
    val sortedImages = images.sortWith(_.score < _.score)
    val best = sortedImages.take(bestNum)
    BaseStage(Seq.tabulate(Config.SPECIMEN_NUM)(i => best(i % best.size)))
  }

  def best(): ScoredImage = images.sortWith(_.score > _.score).head
}

object MonaFpMain {


  def main(args: Array[String]): Unit = {
    val rand = new Random()
    val byteArray = Files.readAllBytes(Paths.get("src/main/resources/mona_small_gray.raw"))
    val z: ArraySeq[Byte] = ArraySeq(byteArray: _*)
    val perfectImage = BaseImage(z, 256, 382)
    val scoringFunction = (a: Int, b: Int) => (a - b) * (a - b)
    val firstStage = BaseStage.empty(Config.SPECIMEN_NUM, BaseImage.empty(Config.W, Config.H))
    val start = System.currentTimeMillis()

    @tailrec
    def loop(n: Int, stage: BaseStage): BaseStage = {
      println(n)
      if (n % 10 == 0) {
        val best = stage.score(scoringFunction, perfectImage).best()
        best.writeToFile(Paths.get(s"best${n}.raw"))
      }
      if (n == 0) stage
      else loop(n - 1, stage.mutate(rand).score(scoringFunction, perfectImage).generateBestStage(Config.BEST_NUM))
    }

    loop(100, firstStage)

    val end = System.currentTimeMillis()
  }
}

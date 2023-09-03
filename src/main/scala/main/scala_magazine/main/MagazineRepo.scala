package main.scala_magazine.main

import cats.effect.IO


final case class MagazineRepo() {
  var id = 0
  var magazines: List[Magazine] = List[Magazine]()

  def create(createMagazine: CreateMagazine): IO[Magazine] = IO {
    val newBus = createMagazine.toMagazine(id)
    id = id + 1
    magazines = newBus +: magazines
    newBus
  }

  def getAll(): IO[List[Magazine]] = IO(magazines)

  def delete(id: Int): IO[Option[Magazine]] = IO {
    val toDelete = magazines.find(_.id == id)
    toDelete.map { shouldBeRemoved =>
      magazines = magazines.filterNot(u => u == shouldBeRemoved)
      shouldBeRemoved
    }
  }
}
package main.snake_fp

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import main.snake_fp.ExtraFood.randomExtraFood

import scala.util.Random

sealed trait Food {
  val cell: Cell

  def draw(batch: Batch, parentAlpha: Float): Unit = {
    Cell.draw(cell, batch, parentAlpha)
  }
}

case class SimpleFood(cell: Cell) extends Food

object SimpleFood {
  def randomFood(): SimpleFood = {
    val x = Random.between(0, Config.widthInCells)
    val y = Random.between(0, Config.heightInCells)

    SimpleFood(Cell(x, y, new Color(1.0f, 0.0f, 0, 1)))
  }
}

case class ExtraFood(cell: Cell, visible: Boolean, visibilityCounter: Int, hideCounter: Int) extends Food {
  def update(collides: Boolean): ExtraFood = {
    if (collides) hide()
    else {
      if (visible) {
        val newCounter = visibilityCounter - 1
        if (newCounter == 0) hide()
        else this.copy(visibilityCounter = newCounter)
      } else {
        val newCounter = hideCounter - 1
        if(newCounter == 0) randomExtraFood().copy(visible = true)
        else this.copy(hideCounter = newCounter)
      }
    }
  }

  def hide(): ExtraFood = {
    copy(cell = cell.copy(x = -20, y = -20), visible = false, visibilityCounter = 30)
  }
}

object ExtraFood {
  def randomExtraFood(): ExtraFood = {
    val x = Random.between(0, Config.widthInCells)
    val y = Random.between(0, Config.heightInCells)

    ExtraFood(Cell(x, y, new Color(1.0f, 1.0f, 0, 1)), true, 30, Random.between(30, 100))
  }
}
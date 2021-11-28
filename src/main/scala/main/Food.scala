package main

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch

import scala.util.Random

case class Food(cell: Cell)

object Food {
  def randomFood(snake: Snake): Food = {
    val x = Random.between(0, Config.widthInCells)
    val y = Random.between(0, Config.heightInCells)

    Food(Cell(x, y, new Color(1.0f, 0.0f, 0, 1)))
  }

  def draw(food: Food, batch: Batch, parentAlpha: Float): Unit = {
    Cell.draw(food.cell, batch, parentAlpha)
  }
}

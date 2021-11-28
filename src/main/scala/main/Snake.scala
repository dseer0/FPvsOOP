package main

import com.badlogic.gdx.graphics.g2d.Batch
import main.Move.Move

case class Snake(head: Cell, tail: List[Cell], move: Move)


object Snake {
  def move(snake: Snake, move: Move, food: Food): (Snake, Food) = {
    if (food.cell.collides(snake.head)) {
      grow(snake, move, food)
    } else {
      justMove(snake, move, food)
    }
  }

  private def justMove(snake: Snake, move: Move, food: Food): (Snake, Food) = {
    (Snake(snake.head.move(Move.toVector(move)._1, Move.toVector(move)._2), snake.head :: snake.tail.init, move),
      food)
  }

  private def grow(snake: Snake, move: Move, food: Food): (Snake, Food) = {
    (Snake(snake.head.move(Move.toVector(move)._1, Move.toVector(move)._2), snake.head :: snake.tail, move),
      Food.randomFood(snake))
  }

  def draw(snake: Snake, batch: Batch, parentAlpha: Float): Unit = {
    Cell.draw(snake.head, batch, parentAlpha)
    snake.tail.foreach(c => Cell.draw(c, batch, parentAlpha))
  }
}

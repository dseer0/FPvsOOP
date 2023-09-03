package main.snake_fp

import com.badlogic.gdx.graphics.g2d.Batch
import main.snake_fp.Move.Move

case class Snake(head: Cell, tail: List[Cell], move: Move) {
  def isDead: Boolean = head.collides(tail)

}


object Snake {
  def draw(snake: Snake, batch: Batch, parentAlpha: Float): Unit = {
    Cell.draw(snake.head, batch, parentAlpha)
    snake.tail.foreach(c => Cell.draw(c, batch, parentAlpha))
  }

}

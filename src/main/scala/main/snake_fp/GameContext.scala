package main.snake_fp

import com.badlogic.gdx.graphics.Color
import main.snake_fp
import main.snake_fp.Move.Move

case class GameContext(snake: Snake, food: SimpleFood, extraFood: ExtraFood) {
  def nextFrame(withInput: Option[Move]): GameContext = {
    val shouldGrow = food.cell.collides(snake.head)
    val shouldExtraGrow = extraFood.cell.collides(snake.head)
    val newExtraFood = extraFood.update(shouldExtraGrow)
    val newFood = if (shouldGrow) SimpleFood.randomFood() else food
    val moveDestination = withInput.filter(isMovePossible(_, snake.move)).getOrElse(snake.move)
    val newHead = snake.head.move(Move.toVector(moveDestination).x, Move.toVector(moveDestination).y)
    val newTail = {
      if (shouldGrow) snake.head :: snake.tail
      else if(shouldExtraGrow) snake.head.copy() :: snake.head.copy() :: snake.head.copy() :: snake.head.copy() :: snake.tail
      else snake.head :: snake.tail.init
    }
    val newSnake = snake_fp.Snake(newHead, newTail, moveDestination)
    if(newSnake.isDead) GameContext.newGame()
    else GameContext(newSnake, newFood, newExtraFood)
  }

  private def isMovePossible(move: Move, currentMove: Move): Boolean = {
    move match {
      case Move.left => currentMove != Move.right
      case Move.right => currentMove != Move.left
      case Move.up => currentMove != Move.down
      case Move.down => currentMove != Move.up
    }
  }
}

object GameContext {
  def newGame(): GameContext = {
    val c = new Color(1f, 1f, 1f, 1f)
    val snake = snake_fp.Snake(Cell(0, 2, c), List(
      Cell(0, 1, c),
      Cell(0, 0, c)
    ), Move.up)

    val food = SimpleFood.randomFood()
    val extraFood = ExtraFood.randomExtraFood()
    GameContext(snake, food, extraFood)
  }
}
package main

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.{Color, GL20, Texture}
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.{Game, Gdx}
import main.Move.Move

class EngineTest1 extends Game {
  lazy val sprite = new Texture("libgdxlogo.png")
  lazy val batch = new SpriteBatch

  var snake: Snake = _
  var counter = 0
  var food: Food = _
  var lastMove: Option[Move] = None

  override def create(): Unit = {
    val c = new Color(1f, 1f, 1f, 1f)
    snake = Snake(Cell(0, 2, c), List(
      Cell(0, 1, c),
      Cell(0, 0, c)
    ), Move.up)

    food = Food.randomFood(snake)
    val (x, y) = Snake.move(snake, Move.right, food)
    snake = x
    food = y //TODO poprawic

  }

  override def render(): Unit = {
    counter = counter + 1
    lastMove = InputStatus.getStatus().move match {
      case Some(value) => Some(value)
      case None => lastMove
    }
    if (counter == 20) {
      logic(lastMove)
      counter = 0
    }
    draw()
  }


  def draw(): Unit = {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin()
    Food.draw(food, batch, 1.0f)
    Snake.draw(snake, batch, 1.0f)
    batch.end()
  }

  def logic(move: Option[Move]): Unit = {
    val (x, y) = move.map(m => Snake.move(snake, m, food))
      .getOrElse(Snake.move(snake, snake.move, food))
    snake = x
    food = y //TODO poprawic
    lastMove = None//
  }
}

package main.snake_fp

import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}
import com.badlogic.gdx.graphics.{GL20, Texture}
import com.badlogic.gdx.{Game, Gdx}
import main.snake_fp.Move.Move

class GameMain extends Game {
  lazy val sprite = new Texture("libgdxlogo.png")
  lazy val batch = new SpriteBatch

  var gameContext: GameContext = _
  var lastClickedMove: Option[Move] = None
  var font: BitmapFont = _

  override def create(): Unit = {
    gameContext = GameContext.newGame()
    font = new BitmapFont

  }

  override def render(): Unit = {
    val lastMove = InputStatus.getStatus().move match {
      case Some(value) => Some(value)
      case None => lastClickedMove
    }
    lastClickedMove = lastMove


    gameContext = gameContext.nextFrame(lastMove)
    draw(gameContext)
  }

  def mainLoop(gameContext: GameContext): Unit = {
    val move = InputStatus.getStatus().move
    val nextFrameContext = gameContext.nextFrame(move)
    draw(gameContext)
    mainLoop(nextFrameContext)
  }


  def draw(gameContext: GameContext): Unit = {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin()
    gameContext.food.draw(batch, 1.0f)
    gameContext.extraFood.draw(batch, 1.0f)
    Snake.draw(gameContext.snake, batch, 1.0f)

    font.getData.setScale(2f)
    font.draw(batch, "score: " + (gameContext.snake.tail.size - 2), 10, 40)

    batch.end()
    println(Gdx.graphics.getFramesPerSecond)
  }

}

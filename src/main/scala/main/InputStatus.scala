package main

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.InputProcessor
import main.Move.Move


case class InputStatus(move: Option[Move])

object InputStatus {
  def getStatus(): InputStatus = {
    import com.badlogic.gdx.Gdx
    import com.badlogic.gdx.Input
    if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) InputStatus(Some(Move.left))
    else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) InputStatus(Some(Move.right))
    else if(Gdx.input.isKeyPressed(Input.Keys.UP)) InputStatus(Some(Move.up))
    else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) InputStatus(Some(Move.down))
    else InputStatus(None)
  }
}

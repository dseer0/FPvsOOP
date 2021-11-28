package main


import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}

object Main extends App {
  val config = new LwjglApplicationConfiguration
  config.title = "Snake"
  config.width = Config.cellSize * Config.widthInCells
  config.height = Config.cellSize * Config.heightInCells
  config.forceExit = true
  config.foregroundFPS = 60




  new LwjglApplication(new EngineTest1(), config)
}

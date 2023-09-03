package main.snake_fp

import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}

object Main extends App {
  val config = new LwjglApplicationConfiguration
  config.title = "Snake"
  config.width = Config.cellSize * Config.widthInCells
  config.height = Config.cellSize * Config.heightInCells
  config.forceExit = true
//  config.foregroundFPS = 1000

  config.vSyncEnabled = false // Setting to false disables vertical sync

  config.foregroundFPS = 0 // Setting to 0 disables foreground fps throttling

  config.backgroundFPS = 0 // Setting to 0 disables background fps throttling


  new LwjglApplication(new GameMain(), config)
}

package main.snake_oop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Snake";
        config.width = Config.cellSize * Config.windowSize;
        config.height = Config.cellSize * Config.windowSize;
        config.forceExit = true;
        config.vSyncEnabled = false;
        config.backgroundFPS = 60;
        new LwjglApplication(new SnakeGame(), config);
    }
}

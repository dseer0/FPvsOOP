package main.snake_oop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SnakeGame extends Game {

    private SpriteBatch batch;
    Snake snake;
    Food food;
    ExtraFood extraFood;
    BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        snake = new Snake();
        food = new Food();
        extraFood = new ExtraFood();
        font = new BitmapFont();
    }


    @Override
    public void render() {
        mainLoop();
        System.out.println(Gdx.graphics.getFramesPerSecond());

    }

    private void drawScene() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        snake.draw(batch);
        food.draw(batch);
        extraFood.draw(batch);
        font.getData().setScale(2f);
        font.draw(batch, "score: " + (snake.Body.size() - 4), 10, 40);
        batch.end();
    }

    private void mainLoop() {
        if (snake.collides(food.cell)) {
            food.randomFood();
            snake.grow(1);
        }

        if (snake.collides(extraFood.cell)) {
            snake.grow(3);
            extraFood.hide();
        }

        snake.turn();
        snake.move();

        if (snake.isDead()) {
            snake = new Snake();
            food = new Food();
            extraFood = new ExtraFood();

        }
        extraFood.update();
        drawScene();
    }


}

package main.snake_oop;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Food {
    protected Cell cell;

    public Food() {
        cell = new Cell(0, 0, Color.RED);
        randomFood();
    }

    public void draw(Batch batch) {
        cell.draw(batch);
    }

    public void randomFood() {
        cell.x = getRandomNumber(0, 30);
        cell.y = getRandomNumber(0, 30);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}

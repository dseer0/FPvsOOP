package main.snake_oop;

import com.badlogic.gdx.graphics.Color;

public class ExtraFood extends Food {

    public int counter;
    public int counter2;
    public boolean isVisible;

    public ExtraFood() {
        isVisible = false;
        counter2 = getRandomNumber(30, 150);
        counter = 30;
        cell = new Cell(-15, -15, Color.GREEN);

    }

    public void hide() {
        this.cell.y = -20;
        this.cell.x = -20;
        this.counter2 = this.getRandomNumber(30, 150);
        this.isVisible = false;
        this.counter = 30;
    }

    public void update() {
        if (isVisible) {
            counter -= 1;
            if (counter == 0) {
                counter = 30;
                cell.x = -10;
                cell.y = -10;
                isVisible = false;
            }
        } else {
            counter2 -= 1;
            if (counter2 == 0) {
                counter2 = getRandomNumber(10, 150);
                isVisible = true;
                randomFood();
            }
        }
    }
}


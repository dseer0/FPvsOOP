package main.snake_oop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

enum Direction {
    Up,
    Left,
    Down,
    Right
}

public class Snake {
    List<Cell> Body = new LinkedList<>();
    public Direction direction;

    public Snake() {
        direction = Direction.Right;
        Cell c1 = new Cell(3, 2, Color.BLUE);
        Cell c2 = new Cell(2, 2, Color.BLUE);
        Cell c3 = new Cell(1, 2, Color.BLUE);
        Cell c4 = new Cell(0, 2, Color.BLUE);

        Body.add(c1);
        Body.add(c2);
        Body.add(c3);
        Body.add(c4);
    }

    public void grow(Integer nTimes) {
        for (int i = 0; i < nTimes; i++)
            Body.add(new Cell(0, 0, Color.BLUE));
    }

    public Boolean collides(Cell withCell) {
        return Body.get(0).collides(withCell);
    }

    public void turn() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && direction != Direction.Left)
            direction = Direction.Right;
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && direction != Direction.Right)
            direction = Direction.Left;
        else if (Gdx.input.isKeyPressed(Input.Keys.UP) && direction != Direction.Down)
            direction = Direction.Up;
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && direction != Direction.Up)
            direction = Direction.Down;
    }

    private void moveImplementation(int xOffset, int yOffset) {
        Cell glowa = Body.get(0);
        Cell ogon = Body.get(Body.size() - 1);
        ogon.x = glowa.x + xOffset;
        ogon.y = glowa.y + yOffset;
        Body.remove(Body.size() - 1);
        Body.add(0, ogon);
    }

    public void move() {
        if (direction == Direction.Right) moveImplementation(1, 0);
        else if (direction == Direction.Left) moveImplementation(-1, 0);
        else if (direction == Direction.Up) moveImplementation(0, 1);
        else if (direction == Direction.Down) moveImplementation(0, -1);
    }

    private void fixHead() {
        Cell head = Body.get(0);
        if (head.x > 29) head.x = 0;
        else if (head.x < 0) head.x = 29;
        else if (head.y > 29) head.y = 0;
        else if (head.y < 0) head.y = 29;
    }


    public void draw(Batch batch) {
        for (Cell cell : Body) cell.draw(batch);
    }

    public boolean isDead() {
        Cell head = Body.get(0);
        List<Cell> body = Body.subList(1, Body.size());
        for (Cell el : body) {
            if (head.x == el.x && head.y == el.y) {
                return true;
            }
        }
        return false;

    }
}

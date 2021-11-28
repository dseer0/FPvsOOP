package main

import com.badlogic.gdx.graphics.{Color, Pixmap, Texture}
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor

case class Cell(x: Int, y: Int, color: Color, texture: Texture) {
  def move(byX: Int, byY: Int): Cell = {
    Cell(this.x + byX, this.y + byY, color, texture)
  }

  def collides(cell: Cell): Boolean = {
    cell.x == x && cell.y == y
  }
}

object Cell {

  def apply(x: Int, y: Int, color: Color): Cell = {
    val texture = createTexture(Config.cellSize, Config.cellSize, color)
    Cell(x, y, color, texture)
  }

  private def createTexture(width: Int, height: Int, color: Color): Texture = {
    val pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888)
    pixmap.setColor(color)
    pixmap.fillRectangle(0, 0, width, height)
    val t = new Texture(pixmap)
    pixmap.dispose()
    t
  }



  def draw(cell: Cell, batch: Batch, parentAlpha: Float): Unit = {
    val color = cell.color
    batch.setColor(color.r, color.g, color.b, color.a * parentAlpha)
    batch.draw(cell.texture, cell.x * Config.cellSize, cell.y * Config.cellSize, Config.cellSize, Config.cellSize)
  }
}

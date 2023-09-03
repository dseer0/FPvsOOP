package main.snake_fp

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.{Color, Pixmap, Texture}

case class Cell(x: Int, y: Int, color: Color, texture: Texture) {
  def move(byX: Int, byY: Int): Cell = {
    Cell(this.x + byX, this.y + byY, color, texture).fixOffsets
  }

  private def fixOffsets: Cell = {
    if(this.x >= Config.widthInCells) this.copy(x = 0)
    else if(this.x < 0) this.copy(x = Config.widthInCells - 1)
    else if (this.y >= Config.heightInCells) this.copy(y = 0)
    else if (this.y < 0) this.copy(y = Config.heightInCells - 1)
    else this
  }

  def collides(cell: Cell): Boolean = {
    cell.x == x && cell.y == y
  }

  def collides(cells: Seq[Cell]): Boolean = {
    cells.exists(this.collides)
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
    val texture = new Texture(pixmap)
    pixmap.dispose()
    texture
  }



  def draw(cell: Cell, batch: Batch, parentAlpha: Float): Unit = {
    val color = cell.color
    batch.setColor(color.r, color.g, color.b, color.a * parentAlpha)
    batch.draw(cell.texture, cell.x * Config.cellSize, cell.y * Config.cellSize, Config.cellSize, Config.cellSize)
  }
}

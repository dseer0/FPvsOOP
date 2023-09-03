package main.snake_fp

case class MoveVector(x: Int, y: Int)
object Move extends Enumeration {
  type Move = Value
  val left, right, up, down = Value

  def toVector(move: Move): MoveVector = {
    move match {
      case Move.left => MoveVector(-1,0)
      case Move.right => MoveVector(1,0)
      case Move.down => MoveVector(0, -1)
      case Move.up => MoveVector(0, 1)
    }
  }
}

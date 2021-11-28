package main

object Move extends Enumeration {
  type Move = Value
  val left, right, up, down = Value

  def toVector(move: Move): (Int, Int) = {
    move match {
      case Move.left => (-1,0)
      case Move.right => (1,0)
      case Move.down => (0, -1)
      case Move.up => (0, 1)
    }
  }
}

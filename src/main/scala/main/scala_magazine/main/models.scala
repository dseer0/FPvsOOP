package main.scala_magazine.main

case class Magazine(id: Int, name: String)
case class CreateMagazine(name: String) {
  def toMagazine(withId: Int): Magazine = Magazine(withId, name)
}


case class Item(id: Int, name: String, magazineId: Int)
case class CreateItem(name: String, magazineId: Int) {
  def toItem(withId: Int): Item = Item(withId, name, magazineId)
}

case class ApiError(msg: String)
package main.scala_magazine.main

import cats.effect.IO


final case class ItemRepo(magazineRepo: MagazineRepo) {
  var id = 0
  var items: List[Item] = List[Item]()

  def create(createItem: CreateItem): IO[Either[ApiError, Item]] = {
    magazineRepo.getAll().map { magazines =>
      if (magazines.exists(_.id == createItem.magazineId)) {
        val newItem = createItem.toItem(id)
        id = id + 1
        items = newItem +: items
        Right(newItem)
      } else {
        Left(ApiError(s"No magazine with id: ${createItem.magazineId}"))
      }
    }

  }

  def getAll(): IO[List[Item]] = IO {
    items
  }

  def delete(id: Integer): IO[Option[Item]] = IO {
    val toDelete = items.find(_.id == id)
    toDelete.map { shouldBeRemoved =>
      items = items.filterNot(u => u == shouldBeRemoved)
      shouldBeRemoved
    }
  }

  def findItemByName(name: String): IO[Either[ApiError, Item]] = IO {
    items.find(_.name == name)
      .map(item => Right(item))
      .getOrElse(Left(ApiError(s"No such item: $name")))
  }

  def findItemById(id: Int): IO[Either[ApiError, Item]] = IO {
    items.find(_.id == id)
      .map(item => Right(item))
      .getOrElse(Left(ApiError(s"No such item with id: $id")))
  }

  def findWithMagazineId(id: Int): IO[Seq[Item]] = IO(items.filter(_.magazineId == id))
}
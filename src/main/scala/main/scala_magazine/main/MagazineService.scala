package main.scala_magazine.main

import cats.effect.IO


final case class MagazineService private(repo: MagazineRepo, itemRepo: ItemRepo) {
  def create(createMagazine: CreateMagazine): IO[Magazine] = repo.create(createMagazine)

  def getAll(): IO[List[Magazine]] = repo.getAll()

  def delete(id: Int): IO[Option[Magazine]] = repo.delete(id)

  def deleteItem(id: Int): IO[Option[Item]] = itemRepo.delete(id)

  def createItem(createItem: CreateItem): IO[Either[ApiError, Item]] = itemRepo.create(createItem)

  def findItemByName(name: String): IO[Either[ApiError, Item]] = itemRepo.findItemByName(name)

  def findItemById(id: Int): IO[Either[ApiError, Item]] = itemRepo.findItemById(id)

  def getAllItemsFromMagazine(magazineId: Int): IO[Seq[Item]] = itemRepo.findWithMagazineId(magazineId)
}
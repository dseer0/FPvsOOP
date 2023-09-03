package main.scala_magazine.main

import cats.effect.IO
import io.circe.generic.auto._
import io.circe.syntax.EncoderOps
import org.http4s.HttpRoutes
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router

final class MagazineRoutes(magazineService: MagazineService) extends Http4sDsl[IO] {
  private val prefixPath = "/magazine"

  private val httpRoutes = HttpRoutes.of[IO] {
    case GET -> Root / "all" => Ok(magazineService.getAll().map(_.asJson))
    case GET -> Root / "itemId" / id => Ok(magazineService.findItemById(id.toInt).map(_.asJson))
    case GET -> Root / "item" / name => Ok(magazineService.findItemByName(name).map(_.asJson))
    case GET -> Root / "items" / id => Ok(magazineService.getAllItemsFromMagazine(id.toInt).map(_.asJson))

    case req@POST -> Root =>
      req.asJsonDecode[CreateMagazine].flatMap { createBus =>
        magazineService.create(createBus).flatMap(added => Ok(added.asJson))
      }

    case req@POST -> Root / "item" =>
      req.asJsonDecode[CreateItem].flatMap { createItem =>
        magazineService.createItem(createItem).flatMap(added => Ok(added.asJson))
      }

    case DELETE -> Root / id =>
      magazineService.delete(Integer.valueOf(id)).flatMap(removed => Ok(removed.asJson))

    case DELETE -> Root / "item" / id =>
      magazineService.deleteItem(Integer.valueOf(id)).flatMap(removed => Ok(removed.asJson))
  }

  val routes = Router(
    prefixPath -> (httpRoutes)
  )
}
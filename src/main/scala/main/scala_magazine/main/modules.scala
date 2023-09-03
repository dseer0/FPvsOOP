package main.scala_magazine.main

import cats.effect.IO
import org.http4s.HttpApp
import org.http4s.implicits._
import org.http4s.server.middleware.{CORS, CORSConfig}

object Repositories {
  def make(): Repositories = {
    val magazines = MagazineRepo()
    val items = ItemRepo(magazines)
    new Repositories(magazines, items)
  }
}

final case class Repositories private(magazineRepo: MagazineRepo, itemRepo: ItemRepo)

object HttpApi {
  def make(services: Services): HttpApi = {
    new HttpApi(services)
  }
}

final class HttpApi private(services: Services) {
  val magazineRoutes = new MagazineRoutes(services.magazineService).routes
  private val config = CORSConfig.default
    .withAnyOrigin(true)
  val httpApp: HttpApp[IO] = CORS(magazineRoutes, config).orNotFound
}


object Services {
  def make(repositories: Repositories): Services = {
    val magazineService = MagazineService(repositories.magazineRepo, repositories.itemRepo)
    new Services(magazineService)
  }

}

final class Services private(val magazineService: MagazineService)


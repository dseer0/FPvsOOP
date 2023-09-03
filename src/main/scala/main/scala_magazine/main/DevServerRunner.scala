package main.scala_magazine.main


import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.blaze.server.BlazeServerBuilder

import scala.concurrent.ExecutionContext.global

object DevServerRunner extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    val repos = Repositories.make()
    val services = Services.make(repos)
    val api = HttpApi.make(services)

    BlazeServerBuilder[IO](global)
      .bindHttp(8080, "127.0.0.1")
      .withHttpApp(api.httpApp)
      .resource
      .use(_ => IO.never)
      .as(ExitCode.Success)
  }
}

name := "SnakeInScala"

version := "0.1"

scalaVersion := "2.13.7"

val Http4sVersion = "0.22.0"
val CirceVersion = "0.14.1"

libraryDependencies ++= Seq(
  "com.badlogicgames.gdx" % "gdx-backend-lwjgl" % "1.9.9",
  "com.badlogicgames.gdx" % "gdx-platform" % "1.9.9" classifier "natives-desktop",
  "io.vavr" % "vavr" % "0.10.0",

  "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
  "org.http4s" %% "http4s-circe" % Http4sVersion,
  "org.http4s" %% "http4s-dsl" % Http4sVersion,
  "io.circe" %% "circe-core" % CirceVersion,
  "io.circe" %% "circe-generic" % CirceVersion,
  "io.circe" %% "circe-parser" % CirceVersion,
  "io.circe" %% "circe-generic-extras" % CirceVersion,
  "ch.qos.logback" % "logback-classic" % "1.2.3"

)


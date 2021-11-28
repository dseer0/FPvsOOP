name := "SnakeInScala"

version := "0.1"

scalaVersion := "2.13.7"

libraryDependencies ++= Seq (
  "com.badlogicgames.gdx" % "gdx-backend-lwjgl" % "1.9.9",
  "com.badlogicgames.gdx" % "gdx-platform" % "1.9.9" classifier "natives-desktop",
)

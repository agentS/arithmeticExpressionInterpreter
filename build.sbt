import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "org.schoerghuber.lukas",
      scalaVersion := "2.12.1",
      version      := "0"
    )),
    name := "arithmeticexpressioninterpreter",
    mainClass := Some("org.schoerghuber.lukas.arithmeticexpressioninterpreter.Application"),
    libraryDependencies += scalaTest % Test
  )

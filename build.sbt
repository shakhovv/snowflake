import Dependencies._

lazy val snowflake = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.snowflake",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "snowflake",
    libraryDependencies ++= Seq(akkaHttp, scalaTest % Test)
  )

import Dependencies._
import play.sbt.PlayImport.PlayKeys._
import com.typesafe.sbt.packager.docker._

ThisBuild / scalaVersion := "2.13.8"
ThisBuild / version := "1.1.0"
ThisBuild / organization := "com.michaelmaysonet74"
ThisBuild / organizationName := "michaelmaysonet74"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """lease-miles-calculator""",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      macwireMacros % Provided,
      macwireUtil
    ),
    playDefaultPort := 8090
  )
  .settings(
    dockerExposedPorts ++= Seq(9000),
    dockerChmodType := DockerChmodType.UserGroupWriteExecute,
    dockerPermissionStrategy := DockerPermissionStrategy.CopyChown,
    dockerEnvVars := Map(
      "TERM_START_DATE" -> "09-06-2021",
      "TERM_LENGTH_MONTHS" -> "36",
      "TERM_MILES_BALANCE" -> "30015",
      "LOCAL_TIME_ZONE" -> "America/Chicago"
    )
  )

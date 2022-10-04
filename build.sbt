import play.sbt.PlayImport.PlayKeys._
import com.typesafe.sbt.packager.docker._

val macwireVersion = "2.5.7"

lazy val server = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """lease-miles-calculator""",
    organization := "com.michaelmaysonet74",
    version := "1.0.1",
    scalaVersion := "2.13.8",
    playDefaultPort := 8090,
    libraryDependencies ++= Seq(
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
      "com.softwaremill.macwire" %% "macros" % macwireVersion % Provided,
      "com.softwaremill.macwire" %% "util" % macwireVersion
    )
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

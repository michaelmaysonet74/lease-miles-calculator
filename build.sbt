val macwireVersion = "2.5.7"

lazy val server = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """lease-miles-calculator""",
    organization := "com.michaelmaysonet74",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.8",
    libraryDependencies ++= Seq(
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
      "com.rallyhealth" %% "weepickle-v1" % "1.7.2",
      "com.softwaremill.macwire" %% "macros" % macwireVersion % Provided,
      "com.softwaremill.macwire" %% "util" % macwireVersion
    )
  )

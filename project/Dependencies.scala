import sbt._

object Dependencies {

  private val macwireVersion = "2.5.7"

  lazy val scalaTest = "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0"
  lazy val macwireMacros = "com.softwaremill.macwire" %% "macros" % macwireVersion
  lazy val macwireUtil = "com.softwaremill.macwire" %% "util" % macwireVersion

}

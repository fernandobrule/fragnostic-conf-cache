import sbt._
import Keys._

object Dependencies {

  lazy val fragnosticConfEnv = "com.fragnostic"   %  "fragnostic-conf-env_2.13"   % "0.1.5"

  lazy val lettuce =           "io.lettuce"       %  "lettuce-core"               % "6.1.2.RELEASE"

  lazy val logbackClassic      = "ch.qos.logback" % "logback-classic"            % "1.2.3" % "runtime"
  lazy val slf4jApi            = "org.slf4j"      % "slf4j-api"                  % "1.7.25" % "runtime"
  lazy val scalatest           = "org.scalatest" %% "scalatest"                  % "3.2.2" % "test"

}

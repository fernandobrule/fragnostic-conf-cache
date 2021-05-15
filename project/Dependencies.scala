import sbt._
import Keys._

object Dependencies {
  
  //private val specs2Version = "4.9.4"
  
  lazy val logbackClassic =    "ch.qos.logback"   %  "logback-classic"          % "1.2.3" % "runtime"
  lazy val slf4jApi =          "org.slf4j"        %  "slf4j-api"                % "1.7.25"
  lazy val scalatest =         "org.scalatest"    %% "scalatest"                % "3.0.8" % "test"
  lazy val lettuce =           "io.lettuce"       %  "lettuce-core"             % "6.1.2.RELEASE"
  lazy val fragnosticConfEnv = "com.fragnostic"   %  "fragnostic-conf-env_2.13" % "0.1.3"
  
  /*lazy val specs2 =  Seq("org.specs2" %% "specs2-core",
                         "org.specs2" %% "specs2-mock",
                         "org.specs2" %% "specs2-matcher-extra").map( _ % specs2Version)*/


}

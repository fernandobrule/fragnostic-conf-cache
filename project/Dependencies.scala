import sbt._

object Dependencies {

  lazy val fragnosticConfEnv          = "com.fragnostic"        %  "fragnostic-conf-env_2.13"      % "0.1.9"

  lazy val lettuce                    = "io.lettuce"            %  "lettuce-core"                  % "6.1.5.RELEASE"
  lazy val logbackClassic             = "ch.qos.logback"        % "logback-classic"                % "1.3.0-alpha12" % "runtime"
  lazy val slf4jApi                   = "org.slf4j"             % "slf4j-api"                      % "2.0.0-alpha5"
  lazy val scalatestFunSpec           = "org.scalatest"        %% "scalatest-funspec"              % "3.3.0-SNAP3" % Test

}

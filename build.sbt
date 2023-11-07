ThisBuild / version := "0.1.0"
ThisBuild / organization := "com.github.gilcu2"
ThisBuild / homepage := Some(url("https://github.com/gilcu2/sbt-bump-version"))

lazy val root = (project in file("."))
  .enablePlugins(SbtPlugin)
  .settings(
      name := "sbt-bump-version",
      pluginCrossBuild / sbtVersion := {
          scalaBinaryVersion.value match {
              case "2.12" => "1.2.8" // set minimum sbt version
          }
      }
  )
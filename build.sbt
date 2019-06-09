name := "sbt-bump-version"
organization := "com.github.reugn"

crossSbtVersions := List("0.13.18", "1.2.8")

val sbtCrossVersion = sbtVersion in pluginCrossBuild
scalaVersion := (CrossVersion partialVersion sbtCrossVersion.value match {
    case Some((0, 13)) => "2.10.7"
    case Some((1, _))  => "2.12.8"
    case _             => sys error s"Unhandled sbt version ${sbtCrossVersion.value}"
})

sbtPlugin := true
publishMavenStyle := true

scalacOptions := Seq(
    "-unchecked",
    "-deprecation"
)

bintrayRepository := "maven"

licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))

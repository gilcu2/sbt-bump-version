package com.github.reugn

import com.github.reugn.VersionLevel.VersionLevel
import sbt.Keys._
import sbt._
import complete.DefaultParsers._

import scala.util.control.NonFatal

object VersionBumpPlugin extends AutoPlugin {

    object autoImport {
        val vbump = inputKey[Unit]("Bump version")
    }

    import autoImport._

    override lazy val projectSettings = Seq(
        vbump := {
            val args = spaceDelimited("").parsed
            streams.value.log.info(s"Current version: ${version.value}")
            if (args.length != 1) {
                streams.value.log.error("Expect version level to update: 'vbump <level>'")
            } else {
                val level = args.head
                val versionFiles = Seq(baseDirectory.value / "build.sbt", baseDirectory.value / "version.sbt")
                try {
                    val updatedVersion = mutateVersion(version.value, VersionLevel.fromStr(level))
                    FileEditor.updateVersionForAll(version.value, updatedVersion, versionFiles)
                    streams.value.log.info(s"Successfully updated version to $updatedVersion")
                } catch {
                    case NonFatal(e) =>
                        e.printStackTrace()
                        streams.value.log.error(s"Failed to update version. ${e.getMessage}")
                }
            }
        }
    )

    private def mutateVersion(version: String, level: VersionLevel): String = {
        VersionParser.update(version, level.toString)
    }

}
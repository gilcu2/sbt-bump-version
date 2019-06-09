package com.github.reugn

import java.security.InvalidParameterException

import scala.util.matching.Regex

object VersionParser {

    val VersionRegex: Regex = """^(((\d+.){2}\d+)([\.\-][\p{L}][\p{L}\d\_\-]+)?(\.\d+)?)$""".r

    def update(version: String, level: String): String = {
        version match {
            case VersionRegex(_, basic, _, ext, tail) =>
                VersionLevel.fromStr(level) match {
                    case VersionLevel.BUILD =>
                        basic + opt(ext) + updateBuild(tail)
                    case VersionLevel.PATCH =>
                        updateLevel(basic, level) + opt(ext)
                    case _ =>
                        updateLevel(basic, level)
                }
            case other@_ =>
                throw new InvalidParameterException(s"Unknown version format $other")
        }
    }

    private def updateBuild(build: String): String = {
        s""".${Option(build).fold(1)(b => b.replace(".", "").toInt + 1)}"""
    }

    private def opt(str: String): String = Option(str).getOrElse("")

    private def updateLevel(v: String, level: String): String = {
        val arr = v.split("\\.").map(_.toInt)
        VersionLevel.fromStr(level) match {
            case VersionLevel.MAJOR =>
                arr(0) += 1
                arr(1) = 0
                arr(2) = 0
            case VersionLevel.MINOR =>
                arr(1) += 1
                arr(2) = 0
            case VersionLevel.PATCH =>
                arr(2) += 1
            case _ =>
                throw new InvalidParameterException("Unknown version level")
        }
        arr.mkString(".")
    }
}

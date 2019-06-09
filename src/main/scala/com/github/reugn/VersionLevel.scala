package com.github.reugn

object VersionLevel extends Enumeration {
    type VersionLevel = Value

    val MAJOR = Value("major")
    val MINOR = Value("minor")
    val PATCH = Value("patch")
    val BUILD = Value("build")

    val UNKNOWN = Value("unknown")

    def fromStr(level: String): VersionLevel = {
        values.find(_.toString == level).getOrElse(UNKNOWN)
    }
}
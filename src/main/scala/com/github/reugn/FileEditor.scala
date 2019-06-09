package com.github.reugn

import java.io._
import java.util.stream.Collectors

object FileEditor {

    def updateVersionForAll(regex: String, replacement: String, files: Seq[File]): Unit = {
        files foreach doReplace(regex, replacement)
    }

    private def doReplace(regex: String, replacement: String)(file: File): Unit = {
        try {
            val reader = new BufferedReader(new FileReader(file))
            val edited = reader.lines().map[String](rMap(regex, replacement)).collect(Collectors.joining("\n"))
            reader.close()
            writeResource(file, edited)
        } catch {
            case _: FileNotFoundException =>
        }
    }

    private def rMap(regex: String, replacement: String)(line: String): String = {
        if (containsVersion(line)) line.replaceFirst(regex, replacement) else line
    }

    private def containsVersion(line: String): Boolean = {
        val clean = line.replace(" ", "")
        clean.contains("version:=") || line.contains("versioninThisBuild:=")
    }

    private def writeResource(file: File, data: String): Unit = {
        val writer = new PrintWriter(file)
        writer.write(data)
        writer.close()
    }
}

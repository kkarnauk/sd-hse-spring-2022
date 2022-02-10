package ru.hse.sd.cli.executor

import java.io.File

object FileContentResources {
    const val resourcesDirPath = "src/test/resources"

    const val engLoremFilename = "$resourcesDirPath/lorem.txt"

    val engLorem = File(engLoremFilename).readText()
    const val rusLoremFilename = "$resourcesDirPath/лорем.txt"

    val rusLorem = File(rusLoremFilename).readText()

    const val notExistsFilename = "$resourcesDirPath/not-exists.txt"
}

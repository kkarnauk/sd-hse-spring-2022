package ru.hse.sd.cli.executor

import java.io.File

object FileContentResources {
    const val engLoremFilename = "src/test/resources/lorem.txt"
    val engLorem = File(engLoremFilename).readText()

    const val rusLoremFilename = "src/test/resources/лорем.txt"
    val rusLorem = File(rusLoremFilename).readText()

    const val notExistsFilename = "src/test/resources/not-exists.txt"
}

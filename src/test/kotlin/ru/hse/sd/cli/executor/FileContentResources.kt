package ru.hse.sd.cli.executor

object FileContentResources {
    const val engLoremFilename = "src/test/resources/lorem.txt"
    val engLorem = """
            Lorem ipsum dolor sit amet, consectetur
            adipiscing elit.
            
        """.trimIndent()

    const val rusLoremFilename = "src/test/resources/лорем.txt"
    val rusLorem = """
            Лорем ипсум долор сит амет, консектетур
            адиписсинг элит.
            
        """.trimIndent()
}

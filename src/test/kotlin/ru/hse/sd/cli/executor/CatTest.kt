package ru.hse.sd.cli.executor

import org.junit.jupiter.api.Test
import ru.hse.sd.cli.command.CodeResult
import kotlin.test.assertTrue

@Suppress("SpellCheckingInspection")
class CatTest : CommandExecutorTest() {
    @Test
    fun `Simple eng cat test`() = withTestContext {
        testFullOutput("cat ${FileContentResources.engLoremFilename}", FileContentResources.engLorem)
    }

    @Test
    fun `Not a file cat test`() = withTestContext {
        test("cat ${FileContentResources.resourcesDirPath}", error = "cat: ${FileContentResources.resourcesDirPath}: Is not a file") {
            assertTrue { it is CodeResult && it.code != 0 }
        }
    }

    @Test
    fun `No file cat test`() = withTestContext {
        test("cat ${FileContentResources.notExistsFilename}", error = "cat: ${FileContentResources.notExistsFilename}: No such file or directory") {
            assertTrue { it is CodeResult && it.code != 0 }
        }
    }

    @Test
    fun `Simple rus cat test`() = withTestContext {
        testFullOutput("cat ${FileContentResources.rusLoremFilename}", FileContentResources.rusLorem)
    }
}

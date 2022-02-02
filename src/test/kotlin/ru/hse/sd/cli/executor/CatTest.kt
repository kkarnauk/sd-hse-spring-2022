package ru.hse.sd.cli.executor

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import ru.hse.sd.cli.command.CodeResult
import ru.hse.sd.cli.command.CommandResult
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Suppress("SpellCheckingInspection")
class CatTest : CommandExecutorTest() {
    @Test
    fun `Simple eng cat test`() = withTestContext {
        testFullOutput("cat $engFile", engLorem)
    }

    @Test
    fun `Not a file cat test`() = withTestContext {
        test("cat src/test/resources", error = "cat: resources: Is not a file") {
            assertTrue { it is CodeResult && it.code != 0 }
        }
    }

    @Test
    @Disabled
    fun `No file cat test`() = withTestContext {
        test("cat src/test/does-not-exist", error = "cat: does-not-exist: No such file or directory") {
            assertTrue { it is CodeResult && it.code != 0 }
        }
    }

    @Test
    fun `Simple rus cat test`() = withTestContext {
        testFullOutput("cat $rusFile", rusLorem)
    }

    // use with caution: only works if @param output ends with new line
    private fun TestContext.testFullOutput(
        command: String,
        output: String,
        expectedResult: CommandResult = CodeResult.success
    ) {
        val result = execute(command)
        assertEquals(expectedResult, result)
        val actualOutput = buildString {
            repeat(output.count { it == '\n' }) {
                append(outputLine())
                append("\n")
            }
        }
        assertEquals(output, actualOutput)
    }

    companion object {

        private const val engFile = "src/test/resources/lorem.txt"
        private val engLorem = """
            Lorem ipsum dolor sit amet, consectetur
            adipiscing elit.
            
        """.trimIndent()

        private const val rusFile = "src/test/resources/лорем.txt"
        private val rusLorem = """
            Лорем ипсум долор сит амет, консектетур
            адиписсинг элит.
            
        """.trimIndent()
    }
}

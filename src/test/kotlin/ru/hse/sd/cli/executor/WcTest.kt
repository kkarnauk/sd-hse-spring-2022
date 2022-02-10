package ru.hse.sd.cli.executor

import org.junit.jupiter.api.Test
import ru.hse.sd.cli.command.CodeResult
import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.assertTrue

@Suppress("SpellCheckingInspection")
class WcTest : CommandExecutorTest() {
    @Test
    fun `Simple eng wc`() = withTestContext {
        val path = FileContentResources.engLoremFilename
        val bytes = Files.readAllBytes(Path.of(path)).size
        test("wc $path", "\t2\t8\t$bytes")
    }

    @Test
    fun `Simple rus wc`() = withTestContext {
        val path = FileContentResources.rusLoremFilename
        val bytes = Files.readAllBytes(Path.of(path)).size
        test("wc $path", "\t2\t8\t$bytes")
    }

    @Test
    fun `Not a file wc test`() = withTestContext {
        test("wc ${FileContentResources.resourcesDirPath}", error = "wc: ${FileContentResources.resourcesDirPath}: Is not a file") {
            assertTrue { it is CodeResult && it.code != 0 }
        }
    }

    @Test
    fun `No file cat test`() = withTestContext {
        test("wc ${FileContentResources.notExistsFilename}", error = "wc: ${FileContentResources.notExistsFilename}: No such file or directory") {
            assertTrue { it is CodeResult && it.code != 0 }
        }
    }

    @Test
    fun `Wc one quote`() = withTestContext {
        test("wc \"", expectedResult = CodeResult.internalError)
        test("wc  \'", expectedResult = CodeResult.internalError)
    }
}

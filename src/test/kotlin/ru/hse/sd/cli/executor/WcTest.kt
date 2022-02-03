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
        val path = "src/test/resources/lorem.txt"
        val bytes = Files.readAllBytes(Path.of(path)).size
        test("wc $path", "\t2\t8\t$bytes")
    }

    @Test
    fun `Simple rus wc`() = withTestContext {
        val path = "src/test/resources/лорем.txt"
        val bytes = Files.readAllBytes(Path.of(path)).size
        test("wc $path", "\t2\t8\t$bytes")
    }

    @Test
    fun `Not a file wc test`() = withTestContext {
        test("wc src/test/resources", error = "wc: resources: Is not a file") {
            assertTrue { it is CodeResult && it.code != 0 }
        }
    }

    @Test
    fun `No file cat test`() = withTestContext {
        test("wc src/test/does-not-exist", error = "wc: does-not-exist: Is not a file") {
            assertTrue { it is CodeResult && it.code != 0 }
        }
    }
}

package ru.hse.sd.cli.executor

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import ru.hse.sd.cli.command.CodeResult
import kotlin.test.assertTrue

@Suppress("SpellCheckingInspection")
class WcTest : CommandExecutorTest() {
    @Test
    @Disabled
    fun `Simple eng wc`() = withTestContext {
        test("wc src/test/resources/lorem.txt", "\t2\t8\t57")
    }

    @Test
    @Disabled
    fun `Simple rus wc`() = withTestContext {
        test("wc src/test/resources/лорем.txt", "\t2\t8\t104")
    }

    @Test
    fun `Not a file wc test`() = withTestContext {
        test("wc src/test/resources", error = "wc: resources: Is not a file") {
            assertTrue { it is CodeResult && it.code != 0 }
        }
    }

    @Test
    @Disabled
    fun `No file cat test`() = withTestContext {
        test("wc src/test/does-not-exist", error = "wc: does-not-exist: No such file or directory") {
            assertTrue { it is CodeResult && it.code != 0 }
        }
    }
}

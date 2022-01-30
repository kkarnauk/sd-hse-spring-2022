package ru.hse.sd.cli.executor

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import ru.hse.sd.cli.command.CodeResult
import ru.hse.sd.cli.command.ExitResult
import kotlin.test.assertTrue

class ExitTest : CommandExecutorTest() {
    @Test
    fun `Simple exit`() = withTestContext {
        test("exit", expectedResult = ExitResult)
    }

    @Test
    @Disabled
    fun `Exit with arguments`() = withTestContext {
        test("exit argument", error = "exit: unexpected arguments") {
            assertTrue(it is CodeResult && it.code != 0)
        }
    }
}

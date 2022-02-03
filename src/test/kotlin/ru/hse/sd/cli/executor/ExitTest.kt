package ru.hse.sd.cli.executor

import org.junit.jupiter.api.Test
import ru.hse.sd.cli.command.ExitResult

class ExitTest : CommandExecutorTest() {
    @Test
    fun `Simple exit`() = withTestContext {
        test("exit", expectedResult = ExitResult)
    }
}

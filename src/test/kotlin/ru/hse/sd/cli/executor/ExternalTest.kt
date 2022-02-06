package ru.hse.sd.cli.executor

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledOnOs
import org.junit.jupiter.api.condition.OS
import ru.hse.sd.cli.command.CodeResult

abstract class ExternalTest : CommandExecutorTest() {
    override fun withTestContext(block: TestContext.() -> Unit) = super.withTestContext {
        closeInput()
        block()
    }
}

@EnabledOnOs(OS.LINUX, OS.MAC)
class LinuxExternalTest : ExternalTest() {
    @Test
    fun `Simple external command test`() = withTestContext {
        test("head -n 1 src/test/resources/lorem.txt", "Lorem ipsum dolor sit amet, consectetur")
    }

    @Test
    fun `File not found external command test`() = withTestContext {
        test(
            "head -n 1 src/test/resources/does-not-exist.txt",
            error = "head: cannot open 'src/test/resources/does-not-exist.txt' for reading: No such file or directory",
            expectedResult = CodeResult(1)
        )
    }
}

@EnabledOnOs(OS.WINDOWS)
class WindowsExternalTest : ExternalTest() {
    @Test
    fun `Simple external command test`() = withTestContext {
        test(
            "powershell -command \"Get-Content -Head 1 src/test/resources/lorem.txt\"",
            "Lorem ipsum dolor sit amet, consectetur"
        )
    }
}

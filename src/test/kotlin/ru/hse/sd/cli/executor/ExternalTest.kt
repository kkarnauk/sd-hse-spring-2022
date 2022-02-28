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

@EnabledOnOs(OS.LINUX)
class LinuxExternalTest : ExternalTest() {
    @Test
    fun `Simple external command test`() = withTestContext {
        test("head -n 1 ${FileContentResources.engLoremFilename}", "Lorem ipsum dolor sit amet, consectetur")
    }

    @Test
    fun `File not found external command test`() = withTestContext {
        test(
            "head -n 1 ${FileContentResources.notExistsFilename}",
            error = "head: cannot open '${FileContentResources.notExistsFilename}' for reading: No such file or directory",
            expectedResult = CodeResult(1)
        )
    }

    @Test
    fun `Pipe test`() = withTestContext {
        test(
            "head -n 1 ${FileContentResources.engLoremFilename} | wc", "\t1\t6\t40"
        )
    }

    @Test
    fun `Pipe external command test`() = withTestContext {
        test("echo 123 | /bin/cat", "123")
    }
}

@EnabledOnOs(OS.MAC)
class MacOSExternalTest : ExternalTest() {
    @Test
    fun `Simple external command test`() = withTestContext {
        test("head -n 1 ${FileContentResources.engLoremFilename}", "Lorem ipsum dolor sit amet, consectetur")
    }

    @Test
    fun `File not found external command test`() = withTestContext {
        test(
            "head -n 1 ${FileContentResources.notExistsFilename}",
            error = "head: ${FileContentResources.notExistsFilename}: No such file or directory",
            expectedResult = CodeResult(1)
        )
    }

    @Test
    fun `Pipe test`() = withTestContext {
        test(
            "head -n 1 ${FileContentResources.engLoremFilename} | wc", "\t1\t6\t40"
        )
    }

    @Test
    fun `Pipe external command test`() = withTestContext {
        test("echo 123 | /bin/cat", "123")
    }
}

@EnabledOnOs(OS.WINDOWS)
class WindowsExternalTest : ExternalTest() {
    @Test
    fun `Simple external command test`() = withTestContext {
        test(
            "powershell -command \"Get-Content -Head 1 ${FileContentResources.engLoremFilename}\"",
            "Lorem ipsum dolor sit amet, consectetur"
        )
    }

    @Test
    fun `Pipe test`() = withTestContext {
        test(
            "powershell -command \"Get-Content -Head 1 ${FileContentResources.engLoremFilename}\" | wc",
            "\t1\t6\t41"
        )
    }

    @Test
    fun `Pipe external command test`() = withTestContext {
        test(
            "echo 123 | type con",
            "123"
        )
    }
}

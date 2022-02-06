package ru.hse.sd.cli.executor

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledOnOs
import org.junit.jupiter.api.condition.OS

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
        test("head -n 1 src/test/resources/lorem.txt", "Lorem ipsum dolor sit amett, consectetur")
    }
}

@EnabledOnOs(OS.WINDOWS)
class WindowsExternalTest : ExternalTest() {
    @Test
    fun `Simple external command test`() = withTestContext {
        test("powershell -command \"Get-Content -Head 1 src/test/resources/lorem.txt\"", "Lorem ipsum dolor sit amett, consectetur")
    }
}

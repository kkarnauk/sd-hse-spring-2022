package ru.hse.sd.cli.executor

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Suppress("SpellCheckingInspection")
class CommonTest : CommandExecutorTest() {
    @Test
    @Disabled
    fun `Empty string does nothing`() = withTestContext {
        test("")
        test("   ")
        test("echo hehe", "hehe") // to check that there is nothing else in stdout
    }
}

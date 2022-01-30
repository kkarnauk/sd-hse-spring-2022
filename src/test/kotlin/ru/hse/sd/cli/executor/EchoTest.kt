package ru.hse.sd.cli.executor

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Suppress("SpellCheckingInspection")
class EchoTest : CommandExecutorTest() {
    @Test
    fun `Simple echo test`() = withTestContext {
        test("echo 123", "123")
    }

    @Test
    fun `Many echo test`() = withTestContext {
        listOf(
            "echo 123" to "123",
            "echo hehe" to "hehe",
            "echo hello world" to "hello world",
            "echo haha   hehe       hihi" to "haha hehe hihi",
            "echo . 123 .  ." to ". 123 . .",
            "echo \"11  22\"" to "11  22",
            "echo '11  22'" to "11  22",
            "echo \"'''''\"" to "'''''",
            "echo" to ""
        ).forEach {
            test(it)
        }
    }

    @Test
    @Disabled
    fun `Echo token`() = withTestContext {
        test("echo echo", "echo")
        test("echo cat", "cat")
        test("echo exit", "exit")
    }

    @Test
    @Disabled
    fun `Echo in quotes`() = withTestContext {
        test("\"echo\" hehe", "hehe")
    }

    @Test
    @Disabled
    fun `Echo russian`() = withTestContext {
        test("echo Ра си я", "Ра си я")
    }
}

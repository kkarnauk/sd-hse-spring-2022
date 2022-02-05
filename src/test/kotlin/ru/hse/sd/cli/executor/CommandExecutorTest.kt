package ru.hse.sd.cli.executor

import ru.hse.sd.cli.command.CodeResult
import ru.hse.sd.cli.command.CommandExecutor
import ru.hse.sd.cli.command.CommandResult
import ru.hse.sd.cli.util.write
import java.io.Closeable
import java.io.PipedInputStream
import java.io.PipedOutputStream
import kotlin.test.assertEquals

abstract class CommandExecutorTest {
    fun TestContext.test(pair: Pair<String, String>) = test(pair.first, pair.second)

    fun TestContext.test(
        command: String,
        output: String? = null,
        error: String? = null,
        expectedResult: (CommandResult) -> Unit
    ) {
        val result = execute(command)
        expectedResult(result)
        if (output != null) assertEquals(output, outputLine())
        if (error != null) assertEquals(error, errorLine())
    }

    fun TestContext.test(
        command: String,
        output: String? = null,
        error: String? = null,
        expectedResult: CommandResult = CodeResult.success
    ) = test(command, output, error) { assertEquals(it, expectedResult) }

    fun withTestContext(block: TestContext.() -> Unit) {
        PipedInputStream().use { input ->
            PipedOutputStream().use { output ->
                PipedOutputStream().use { error ->
                    TestContext(input, output, error, CommandExecutor(input, output, error)).use(block)
                }
            }
        }
    }

    @Suppress("unused")
    class TestContext internal constructor(
        input: PipedInputStream,
        output: PipedOutputStream,
        error: PipedOutputStream,
        private val executor: CommandExecutor
    ) : Closeable {
        private val toInput = PipedOutputStream(input)
        private val fromOutput = PipedInputStream(output).bufferedReader()
        private val fromError = PipedInputStream(error).bufferedReader()

        fun input(input: String) = toInput.write("$input\n")
        fun outputLine(): String = fromOutput.readLine()
        fun errorLine(): String = fromError.readLine()
        fun execute(command: String) = executor.execute(command)

        override fun close() {
            toInput.close()
            fromOutput.close()
            fromError.close()
        }
    }
}

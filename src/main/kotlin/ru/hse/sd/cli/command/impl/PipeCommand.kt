package ru.hse.sd.cli.command.impl

import ru.hse.sd.cli.command.Command
import ru.hse.sd.cli.command.CommandResult
import ru.hse.sd.cli.env.Environment
import ru.hse.sd.cli.env.IoContext
import java.io.PipedInputStream
import java.io.PipedOutputStream

/**
 * Represents the command that transfer the output of the [left] command to the input of [right] command.
 */
data class PipeCommand(
    /**
     * Left command of the pipe.
     */
    val left: Command,
    /**
     * Right command of the pipe.
     */
    val right: Command
) : Command() {
    /**
     * Executes the pipe command.
     *
     * Data flows in the following way: `context.input` -> [left] -> [right] -> `context.output`.
     */
    override fun execute(context: IoContext, env: Environment): CommandResult {
        val leftOutput = PipedOutputStream()
        val rightInput = PipedInputStream(leftOutput)

        left.execute(IoContext(context.input, leftOutput, context.error), env)
        leftOutput.close()
        return right.execute(IoContext(rightInput, context.output, context.error), env)
    }
}

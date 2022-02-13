package ru.hse.sd.cli.command.impl

import ru.hse.sd.cli.command.CodeResult
import ru.hse.sd.cli.command.Command
import ru.hse.sd.cli.command.CommandResult
import ru.hse.sd.cli.env.Environment
import ru.hse.sd.cli.env.IoContext
import ru.hse.sd.cli.util.write

/**
 * Represents the `echo`-command in CLI.
 * Takes a message from [args] and sends it to output, ignoring input.
 */
data class EchoCommand(
    /**
     * Strings to be printed to output, separated by a space.
     */
    val args: List<String>
) : Command() {
    override fun execute(context: IoContext, env: Environment): CommandResult {
        val message = args.joinToString(separator = " ", postfix = System.lineSeparator())
        context.output.write(message)
        return CodeResult.success
    }
}

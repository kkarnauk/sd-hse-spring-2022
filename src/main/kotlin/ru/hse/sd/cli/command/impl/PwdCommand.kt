package ru.hse.sd.cli.command.impl

import ru.hse.sd.cli.command.CodeResult
import ru.hse.sd.cli.command.Command
import ru.hse.sd.cli.command.CommandResult
import ru.hse.sd.cli.env.Environment
import ru.hse.sd.cli.env.IoContext
import ru.hse.sd.cli.util.write

/**
 * Represents the `pwd`-command in CLI.
 * Prints the current working directory to output.
 */
object PwdCommand : Command() {
    override fun execute(context: IoContext, env: Environment): CommandResult {
        context.output.write(env.workingDirectory.toString() + System.lineSeparator())
        return CodeResult.success
    }
}

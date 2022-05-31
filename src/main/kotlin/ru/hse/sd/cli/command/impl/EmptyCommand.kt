package ru.hse.sd.cli.command.impl

import ru.hse.sd.cli.command.CodeResult
import ru.hse.sd.cli.command.Command
import ru.hse.sd.cli.command.CommandResult
import ru.hse.sd.cli.env.Environment
import ru.hse.sd.cli.env.IoContext

/**
 * Represents command that does nothing, usually represents empty line in cli.
 * Does not do anything with neither input, output nor error stream
 */
object EmptyCommand : Command() {
    override fun execute(context: IoContext, env: Environment): CommandResult = CodeResult.success
}

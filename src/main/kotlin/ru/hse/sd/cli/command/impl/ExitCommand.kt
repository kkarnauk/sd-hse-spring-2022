package ru.hse.sd.cli.command.impl

import ru.hse.sd.cli.command.Command
import ru.hse.sd.cli.command.CommandResult
import ru.hse.sd.cli.command.ExitResult
import ru.hse.sd.cli.env.Environment
import ru.hse.sd.cli.env.IoContext

/**
 * Represents the `exit`-command in CLI.
 * Exists CLI.
 */
object ExitCommand : Command() {
    override fun execute(context: IoContext, env: Environment): CommandResult = ExitResult
}

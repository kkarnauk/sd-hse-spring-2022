package ru.hse.sd.cli.command.impl

import ru.hse.sd.cli.command.Command
import ru.hse.sd.cli.command.CommandResult
import ru.hse.sd.cli.env.Environment
import ru.hse.sd.cli.env.IoContext

/**
 * TODO
 */
data class GrepCommand(
    val args: List<String>
) : Command() {
    override fun execute(context: IoContext, env: Environment): CommandResult {
        TODO() // use https://github.com/ajalt/clikt here to parse args and then execute command
    }
}

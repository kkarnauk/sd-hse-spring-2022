package ru.hse.sd.cli.command

import ru.hse.sd.cli.env.Environment
import ru.hse.sd.cli.env.IoContext

sealed interface Command {
    fun execute(context: IoContext, env: Environment): CommandResult
}

data class EchoCommand(val args: List<String>) : Command {
    override fun execute(context: IoContext, env: Environment): CommandResult = TODO()
}

data class CatCommand(val file: String?) : Command {
    override fun execute(context: IoContext, env: Environment): CommandResult = TODO()
}

data class WcCommand(val file: String?) : Command {
    override fun execute(context: IoContext, env: Environment): CommandResult = TODO()
}

object PwdCommand : Command {
    override fun execute(context: IoContext, env: Environment): CommandResult = TODO()
}

object ExitCommand : Command {
    override fun execute(context: IoContext, env: Environment): CommandResult = TODO()
}

data class ExternalCommand(val name: String) : Command {
    override fun execute(context: IoContext, env: Environment): CommandResult = TODO()
}

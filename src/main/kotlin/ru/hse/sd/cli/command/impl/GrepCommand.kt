package ru.hse.sd.cli.command.impl

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.optional
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
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

private class GrepArgumentsParser : CliktCommand() {
    val ignoreCase by option("-i").flag()
    val wordRegexp by option("-w").flag()
    val afterContext by option("-A").int().default(0)

    val pattern by argument()
    val filename by argument().optional()

    override fun run() {
        println("$ignoreCase $wordRegexp $afterContext")
        println("Pattern=$pattern")
        println("Filename=$filename")
    }
}

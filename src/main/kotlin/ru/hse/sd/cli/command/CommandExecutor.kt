package ru.hse.sd.cli.command

import ru.hse.sd.cli.env.Environment
import ru.hse.sd.cli.env.IoContext
import ru.hse.sd.cli.lang.impl.CommandLexer
import ru.hse.sd.cli.lang.impl.CommandParser
import java.io.InputStream
import java.io.OutputStream

/**
 * Executes different commands using [execute].
 * It uses [CommandParser] and [CommandLexer] to transform a string into [Command] and after that executes it
 * with the context of the provided input, output and error.
 * Also, it collects all the environment variables.
 */
class CommandExecutor(
    /**
     * Input stream of the commands to be executed.
     */
    input: InputStream,
    /**
     * Output stream of the commands to be executed.
     */
    output: OutputStream,
    /**
     * Error stream of the commands to be executed.
     */
    error: OutputStream
) {
    private val environment = Environment()
    private val context = IoContext(input, output, error)
    private val lexer = CommandLexer()
    private val parser = CommandParser()

    private fun parse(command: String): Command = parser.parse(lexer.tokenize(command))

    /**
     * Transforms [command] into [Command] and executes it.
     * Uses the collected environment variables and the provided input/output/error streams.
     */
    fun execute(command: String): CommandResult = parse(command).execute(context, environment)
}

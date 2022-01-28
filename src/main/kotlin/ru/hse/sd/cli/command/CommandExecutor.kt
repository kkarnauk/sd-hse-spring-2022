package ru.hse.sd.cli.command

import ru.hse.sd.cli.env.Environment
import ru.hse.sd.cli.env.IoContext
import ru.hse.sd.cli.lang.impl.CommandLexer
import ru.hse.sd.cli.lang.impl.CommandParser
import java.io.InputStream
import java.io.OutputStream

class CommandExecutor(
    input: InputStream,
    output: OutputStream,
    error: OutputStream
) {
    private val environment = Environment()
    private val context = IoContext(input, output, error)
    private val lexer = CommandLexer()
    private val parser = CommandParser()

    private fun parse(command: String): Command = parser.parse(lexer.tokenize(command))

    fun execute(command: String): CommandResult = parse(command).execute(context, environment)
}

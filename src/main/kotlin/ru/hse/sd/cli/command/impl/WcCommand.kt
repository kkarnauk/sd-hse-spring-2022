package ru.hse.sd.cli.command.impl

import ru.hse.sd.cli.command.CodeResult
import ru.hse.sd.cli.command.Command
import ru.hse.sd.cli.command.CommandResult
import ru.hse.sd.cli.env.Environment
import ru.hse.sd.cli.env.IoContext
import ru.hse.sd.cli.util.write

/**
 * Represents the `wc`-command in CLI.
 * Takes a file as [argument] or a message from input and counts lines, words and symbols in it.
 */
data class WcCommand(
    /**
     * Possible name of the file that used to count lines, words and symbols in it.
     * All this information is sent to output.
     * If the file is not provided, then input is used instead.
     */
    val argument: String?
) : Command() {
    private data class WcResult(
        val linesCount: Int,
        val wordsCount: Int,
        val byteCount: Int
    ) {
        override fun toString(): String = "\t$linesCount\t$wordsCount\t$byteCount"
    }

    private val whitespaceRegex: Regex = Regex("\\s")

    private fun wc(bytes: ByteArray): WcResult {
        val separator = System.lineSeparator()
        return WcResult(
            bytes.asSequence().windowed(size = separator.length, step = 1).count { it.string == separator },
            String(bytes).split(whitespaceRegex).filterNot { it.isEmpty() }.size,
            bytes.size
        )
    }

    private val List<Byte>.string get() = joinToString("") { it.toInt().toChar().toString() }

    override fun execute(context: IoContext, env: Environment): CommandResult {
        return if (argument != null) {
            openAndValidateFile(argument, context.error, env) { file ->
                val wcResult = wc(file.readBytes())
                context.output.write(wcResult.toString())
                context.output.write(System.lineSeparator())
                CodeResult.success
            }
        } else {
            val wcResult = wc(context.input.readAllBytes())
            context.output.write(wcResult.toString())
            context.output.write(System.lineSeparator())
            CodeResult.success
        }
    }
}

package ru.hse.sd.cli.command.impl

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.optional
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import ru.hse.sd.cli.command.CodeResult
import ru.hse.sd.cli.command.Command
import ru.hse.sd.cli.command.CommandResult
import ru.hse.sd.cli.env.Environment
import ru.hse.sd.cli.env.IoContext
import ru.hse.sd.cli.util.write

/**
 * Represents the `grep`-command in CLI.
 * Searches for the given pattern in the provided file if it presents.
 * Otherwise, uses the input stream.
 *
 * Prints all lines with at least one match in the output stream.
 *
 * This grep version supports regexp and 3 flags:
 * * `-i` — ignore case,
 * * `-w` — search only for substrings surrounding by the non-word constituent character,
 * * `-A int_value` — Print `int_value` lines of trailing context after each match. The default value is zero.
 */
data class GrepCommand(
    /**
     * Contains pattern, optional filename and flags with its values.
     */
    val args: List<String>
) : Command() {
    override fun execute(context: IoContext, env: Environment): CommandResult {
        val grepArgs = GrepArgumentsParser().apply { parse(args) }.grepArguments
        val input = if (grepArgs.filename != null) {
            openAndValidateFile(grepArgs.filename, context.error, env, { return CodeResult(1) }) {
                it.readLines()
            }
        } else {
            String(context.input.readAllBytes()).lines()
        }

        val regex = grepArgs.prepareRegex()
        var printUntil = -1
        input.forEachIndexed { index, line ->
            val match = regex.find(line)
            if (match != null && grepArgs.matches(line, match)) {
                printUntil = maxOf(printUntil, index + grepArgs.afterContext)
            }

            if (index <= printUntil) {
                context.output.write(line + System.lineSeparator())
            }
        }

        return CodeResult.success
    }

    private fun GrepArguments.prepareRegex(): Regex {
        return pattern.toRegex(buildSet {
            if (ignoreCase) {
                add(RegexOption.IGNORE_CASE)
            }
        })
    }

    private val Char.isWordConstituent: Boolean
        get() = isLetter() || isDigit() || this == '_'

    private fun GrepArguments.matches(input: String, match: MatchResult): Boolean {
        if (!wordRegexp) {
            return true
        }
        val first = input.getOrNull(match.range.first - 1)
        val last = input.getOrNull(match.range.last + 1)
        return first?.isWordConstituent != true && last?.isWordConstituent != true
    }
}

private data class GrepArguments(
    val pattern: String,
    val filename: String?,
    val ignoreCase: Boolean,
    val wordRegexp: Boolean,
    val afterContext: Int
)

private class GrepArgumentsParser : CliktCommand() {
    private val ignoreCase by option("-i").flag()
    private val wordRegexp by option("-w").flag()
    private val afterContext by option("-A").int().default(0)

    private val pattern by argument()
    private val filename by argument().optional()

    val grepArguments: GrepArguments
        get() = GrepArguments(pattern, filename, ignoreCase, wordRegexp, afterContext)

    override fun run() = Unit
}

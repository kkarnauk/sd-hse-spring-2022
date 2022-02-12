package ru.hse.sd.cli.command

import com.github.h0tk3y.betterParse.lexer.TokenMatchesSequence
import ru.hse.sd.cli.env.Environment
import ru.hse.sd.cli.env.IoContext
import ru.hse.sd.cli.lang.impl.CommandGrammar
import ru.hse.sd.cli.lang.impl.CommandLexer
import ru.hse.sd.cli.lang.impl.CommandParser
import ru.hse.sd.cli.util.write
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

    private fun String.removeUselessQuotes(): String {
        val afterRemoving = removeSurrounding(first().toString())
        return if (afterRemoving.contains(requireQuote)) {
            this
        } else {
            afterRemoving
        }
    }


    private fun processTokens(tokenMatchesSequence: TokenMatchesSequence, environment: Environment): String =
        tokenMatchesSequence.map { token ->
            when (token.type) {
                CommandGrammar.substitutionToken -> environment[token.text.drop(1)]
                CommandGrammar.quoteToken -> token.text.removeUselessQuotes()
                CommandGrammar.doubleQuoteToken -> {
                    token.text.replace(substitutionInDoubleQuote) {
                        environment[it.groupValues.last()]
                    }.removeUselessQuotes()
                }
                else -> token.text
            }
        }.joinToString("")

    private fun parse(command: String, environment: Environment): Command = parser.parse(
        lexer.tokenize(processTokens(lexer.tokenize(command), environment))
    )

    /**
     * Transforms [command] into [Command] and executes it.
     * Uses the collected environment variables and the provided input/output/error streams.
     */
    fun execute(command: String): CommandResult {
        return try {
            parse(command, environment).execute(context, environment)
        } catch (e: Throwable) {
            context.error.write("Internal error: ${e.message ?: "no message."}\n")
            CodeResult.internalError
        }
    }

    companion object {
        private val requireQuote = "[\\s\'\"|$]".toRegex()
        private val substitutionInDoubleQuote = "\\$([^\\s$\"\']+)".toRegex()
    }
}

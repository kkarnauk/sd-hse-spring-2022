package ru.hse.sd.cli.lang.impl

import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.lexer.literalToken
import com.github.h0tk3y.betterParse.lexer.regexToken
import com.github.h0tk3y.betterParse.parser.Parser
import ru.hse.sd.cli.command.*

/**
 * Bash-like CLI command grammar.
 * Can be used for lexing and parsing.
 */
object CommandGrammar : Grammar<Command>() {
    @Suppress("unused")
    internal val wsToken by regexToken("\\s+", ignore = true)
    internal val catToken by literalToken("cat")
    internal val echoToken by literalToken("echo")
    internal val wcToken by literalToken("wc")
    internal val pwdToken by literalToken("pwd")
    internal val exitToken by literalToken("exit")
    internal val identifier by regexToken("(\\w|\\.|/)+")
    internal val quoteToken by regexToken("'[^']*'")
    internal val doubleQuoteToken by regexToken("\"[^\"]*\"")

    private val literal by identifier or quoteToken or doubleQuoteToken map {
        when (it.type) {
            identifier -> it.text
            quoteToken -> it.text.removeSurrounding("'")
            doubleQuoteToken -> it.text.removeSurrounding("\"")
            else -> throw IllegalArgumentException()
        }
    }
    private val echoTerm by echoToken and zeroOrMore(literal) map { EchoCommand(it.t2) }
    private val catTerm by catToken and optional(literal) map { CatCommand(it.t2) }
    private val wcTerm by wcToken and optional(literal) map { WcCommand(it.t2) }
    private val pwdTerm by pwdToken and optional(literal) map { PwdCommand }
    private val exitTerm by exitToken and optional(literal) map { ExitCommand }
    private val externalCommandTerm by literal and zeroOrMore(literal) map { (name, args) ->
        ExternalCommand(name, args)
    }
    private val term by echoTerm or catTerm or wcTerm or pwdTerm or exitTerm or externalCommandTerm

    override val rootParser: Parser<Command> by term
}

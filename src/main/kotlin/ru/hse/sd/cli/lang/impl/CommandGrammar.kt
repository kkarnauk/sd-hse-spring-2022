package ru.hse.sd.cli.lang.impl

import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.lexer.literalToken
import com.github.h0tk3y.betterParse.lexer.regexToken
import com.github.h0tk3y.betterParse.parser.Parser
import ru.hse.sd.cli.command.*

/**
 * Bash command grammar.
 * Can be used for lexing and parsing.
 */
object CommandGrammar : Grammar<Command>() {
    @Suppress("unused")
    val wsToken by regexToken("\\s+", ignore = true)
    val catToken by literalToken("cat")
    val echoToken by literalToken("echo")
    val wcToken by literalToken("wc")
    val pwdToken by literalToken("pwd")
    val exitToken by literalToken("exit")
    val identifier by regexToken("\\w+")
    val quoteToken by regexToken("'[^']*'")
    val doubleQuoteToken by regexToken("\"[^\"]*\"")

    val literal by identifier or quoteToken or doubleQuoteToken map {
        when (it.type) {
            identifier -> it.text
            quoteToken -> it.text.removeSurrounding("'")
            doubleQuoteToken -> it.text.removeSurrounding("\"")
            else -> throw IllegalArgumentException()
        }
    }
    val echoTerm by echoToken and zeroOrMore(literal) map { EchoCommand(it.t2) }
    val catTerm by catToken and optional(literal) map { CatCommand(it.t2) }
    val wcTerm by wcToken and optional(literal) map { WcCommand(it.t2) }
    val pwdTerm by pwdToken and optional(literal) map { PwdCommand }
    val exitTerm by exitToken and optional(literal) map { ExitCommand }
    val externalCommandTerm by literal map { ExternalCommand(it) }
    val term by echoTerm or catTerm or wcTerm or pwdTerm or exitTerm or externalCommandTerm

    override val rootParser: Parser<Command> by term
}

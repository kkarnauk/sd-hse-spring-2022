package ru.hse.sd.cli.lang.impl

import com.github.h0tk3y.betterParse.combinators.and
import com.github.h0tk3y.betterParse.combinators.map
import com.github.h0tk3y.betterParse.combinators.optional
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.lexer.literalToken
import com.github.h0tk3y.betterParse.lexer.regexToken
import com.github.h0tk3y.betterParse.parser.Parser
import ru.hse.sd.cli.command.CatCommand
import ru.hse.sd.cli.command.Command

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

    val catTerm: Parser<Command> by catToken and optional(identifier) map { CatCommand(it.t2?.text) }
    val term: Parser<Command> by catTerm

    override val rootParser: Parser<Command> by term

}

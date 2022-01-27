package ru.hse.sd.cli.lang.impl

import com.github.h0tk3y.betterParse.lexer.TokenMatchesSequence
import com.github.h0tk3y.betterParse.parser.parseToEnd
import ru.hse.sd.cli.command.Command
import ru.hse.sd.cli.lang.Parser

class CommandParser : Parser {
    override fun parse(tokens: TokenMatchesSequence): Command = CommandGrammar.parseToEnd(tokens)
}

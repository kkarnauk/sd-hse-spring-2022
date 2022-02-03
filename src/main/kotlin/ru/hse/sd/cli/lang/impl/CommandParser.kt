package ru.hse.sd.cli.lang.impl

import com.github.h0tk3y.betterParse.lexer.TokenMatchesSequence
import com.github.h0tk3y.betterParse.parser.parseToEnd
import ru.hse.sd.cli.command.Command
import ru.hse.sd.cli.command.EmptyCommand
import ru.hse.sd.cli.lang.Parser

/**
 * Implements a bash command parser using [CommandGrammar]
 */
class CommandParser : Parser {
    /**
     * Implements a bash command parsing using [CommandGrammar]
     */
    override fun parse(tokens: TokenMatchesSequence): Command =
        if (tokens.all { it.type.ignored }) EmptyCommand else CommandGrammar.parseToEnd(tokens)
}

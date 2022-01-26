package ru.hse.sd.cli.command.parser

import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.parser.Parser
import ru.hse.sd.cli.command.Command

class CommandGrammar : Grammar<Command>() {
    override val rootParser: Parser<Command>
        get() = TODO()
}

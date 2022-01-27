package ru.hse.sd.cli.lang

import com.github.h0tk3y.betterParse.lexer.TokenMatchesSequence
import ru.hse.sd.cli.command.Command

interface Parser {
    fun parse(tokens: TokenMatchesSequence): Command
}

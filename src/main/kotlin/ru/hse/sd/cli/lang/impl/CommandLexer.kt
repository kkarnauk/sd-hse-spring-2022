package ru.hse.sd.cli.lang.impl

import com.github.h0tk3y.betterParse.lexer.TokenMatchesSequence
import ru.hse.sd.cli.lang.Lexer

class CommandLexer : Lexer {
    override fun tokenize(string: String): TokenMatchesSequence = CommandGrammar.tokenizer.tokenize(string)
}


package ru.hse.sd.cli.lang

import com.github.h0tk3y.betterParse.lexer.TokenMatchesSequence

interface Lexer {
    fun tokenize(string: String): TokenMatchesSequence
}

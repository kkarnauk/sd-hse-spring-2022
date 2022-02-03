package ru.hse.sd.cli.lang

import com.github.h0tk3y.betterParse.lexer.TokenMatchesSequence

/**
 * Interface for the bash-like CLI lexer
 */
interface Lexer {
    /**
     * Splits [string] into tokens
     */
    fun tokenize(string: String): TokenMatchesSequence
}

package ru.hse.sd.cli.lang.impl

import com.github.h0tk3y.betterParse.lexer.TokenMatchesSequence
import ru.hse.sd.cli.lang.Lexer

/**
 * Implements a bash command lexer using [CommandGrammar]
 */
class CommandLexer : Lexer {
    /**
     * Implements a bash command tokenization using [CommandGrammar]
     */
    override fun tokenize(string: String): TokenMatchesSequence = CommandGrammar.tokenizer.tokenize(string)
}


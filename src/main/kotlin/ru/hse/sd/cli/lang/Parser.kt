package ru.hse.sd.cli.lang

import com.github.h0tk3y.betterParse.lexer.TokenMatchesSequence
import ru.hse.sd.cli.command.Command

/**
 * Interface for the bash command parser
 */
interface Parser {
    /**
     * Build AST tree by [tokens]
     */
    fun parse(tokens: TokenMatchesSequence): Command
}

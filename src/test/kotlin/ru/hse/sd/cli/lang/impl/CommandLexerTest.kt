package ru.hse.sd.cli.lang.impl

import com.github.h0tk3y.betterParse.lexer.Token
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

internal class CommandLexerTest {

    @ParameterizedTest
    @MethodSource("testSource")
    fun `test tokenize`(input: String, expected: List<Pair<Token, String>>) {
        assertEquals(
            expected,
            CommandLexer().tokenize(input)
                .filterNot { it.type.ignored }
                .map { it.type to it.text }
                .toList()
        )
    }

    companion object {
        @JvmStatic
        fun testSource() = listOf(
            Arguments.of("echo", listOf(CommandGrammar.echoToken to "echo")),
            Arguments.of("cat", listOf(CommandGrammar.catToken to "cat")),
            Arguments.of("wc", listOf(CommandGrammar.wcToken to "wc")),
            Arguments.of("pwd", listOf(CommandGrammar.pwdToken to "pwd")),
            Arguments.of("exit", listOf(CommandGrammar.exitToken to "exit")),
            Arguments.of("firefox", listOf(CommandGrammar.identifier to "firefox")),
            Arguments.of("chrome", listOf(CommandGrammar.identifier to "chrome")),
            Arguments.of("telegram", listOf(CommandGrammar.identifier to "telegram")),
            Arguments.of("\n\r\t\n   echo       \n\r\n\t\n    ", listOf(CommandGrammar.echoToken to "echo")),
            Arguments.of(
                "echo arg1 arg2", listOf(
                    CommandGrammar.echoToken to "echo",
                    CommandGrammar.identifier to "arg1",
                    CommandGrammar.identifier to "arg2",
                )
            ),
            Arguments.of(
                "echo 'text'", listOf(
                    CommandGrammar.echoToken to "echo",
                    CommandGrammar.quoteToken to "'text'"
                )
            ),
            Arguments.of(
                "echo 'text1' 'text2'", listOf(
                    CommandGrammar.echoToken to "echo",
                    CommandGrammar.quoteToken to "'text1'",
                    CommandGrammar.quoteToken to "'text2'"
                )
            ),
            Arguments.of(
                "echo 'text \"wow\"'", listOf(
                    CommandGrammar.echoToken to "echo",
                    CommandGrammar.quoteToken to "'text \"wow\"'"
                )
            ),
            Arguments.of(
                "echo \"text\"", listOf(
                    CommandGrammar.echoToken to "echo",
                    CommandGrammar.doubleQuoteToken to "\"text\""
                )
            ),
            Arguments.of(
                "echo \"text1\" \"text2\"", listOf(
                    CommandGrammar.echoToken to "echo",
                    CommandGrammar.doubleQuoteToken to "\"text1\"",
                    CommandGrammar.doubleQuoteToken to "\"text2\""
                )
            ),
            Arguments.of(
                "echo \"text 'wow'\"", listOf(
                    CommandGrammar.echoToken to "echo",
                    CommandGrammar.doubleQuoteToken to "\"text 'wow'\""
                )
            ),
            Arguments.of(
                "cat abc/hello.txt", listOf(
                    CommandGrammar.catToken to "cat",
                    CommandGrammar.identifier to "abc/hello.txt"
                )
            ),
        )
    }
}

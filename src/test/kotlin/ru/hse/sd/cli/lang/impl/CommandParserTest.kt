package ru.hse.sd.cli.lang.impl

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import ru.hse.sd.cli.command.*
import kotlin.test.assertEquals

internal class CommandParserTest {

    @ParameterizedTest
    @MethodSource("testSource")
    fun `test parse`(input: String, expected: Command) {
        assertEquals(
            expected,
            CommandLexer().tokenize(input)
                .let { CommandParser().parse(it) }
        )
    }

    companion object {
        @JvmStatic
        fun testSource() = listOf(
            Arguments.of("echo", EchoCommand(emptyList())),
            Arguments.of(
                "echo 1 2 \"ab c\" 45 'd ef'", EchoCommand(
                    listOf(
                        "1", "2", "ab c", "45", "d ef"
                    )
                )
            ),
            Arguments.of("cat", CatCommand(null)),
            Arguments.of("cat filename", CatCommand("filename")),
            Arguments.of("wc", WcCommand(null)),
            Arguments.of("wc filename", WcCommand("filename")),
            Arguments.of("pwd", PwdCommand),
            Arguments.of("exit", ExitCommand),
            Arguments.of("cd myProject", ExternalCommand("cd", listOf("myProject"))),
            Arguments.of(
                "echo 1 | cat 2 | wc 3",
                PipeCommand(
                    PipeCommand(
                        EchoCommand(listOf("1")),
                        CatCommand("2")
                    ),
                    WcCommand("3")
                )
            ),
            Arguments.of("x=y", AssignmentCommand("x", "y")),
            Arguments.of("x =y", AssignmentCommand("x", "y")),
            Arguments.of("x= y", AssignmentCommand("x", "y")),
            Arguments.of("x = y", AssignmentCommand("x", "y")),
            Arguments.of("echo=echo", AssignmentCommand("echo", "echo")),
            Arguments.of("echo= echo", AssignmentCommand("echo", "echo")),
            Arguments.of("echo =echo", AssignmentCommand("echo", "echo")),
            Arguments.of("echo = echo", AssignmentCommand("echo", "echo")),
        )
    }
}

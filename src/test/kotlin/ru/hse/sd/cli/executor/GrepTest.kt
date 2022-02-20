package ru.hse.sd.cli.executor

import org.junit.jupiter.api.Test
import ru.hse.sd.cli.command.CodeResult
import ru.hse.sd.cli.executor.FileContentResources.engLoremFilename
import ru.hse.sd.cli.executor.FileContentResources.hellosFilename
import ru.hse.sd.cli.executor.FileContentResources.notExistsFilename
import ru.hse.sd.cli.executor.FileContentResources.rusLorem
import ru.hse.sd.cli.executor.FileContentResources.rusLoremFilename

class GrepTest : CommandExecutorTest() {
    private val n = System.lineSeparator()

    @Test
    fun `Grep incorrect configuration`() = withTestContext {
        test(
            "grep -a a $rusLoremFilename",
            error = "Internal error: no such option: \"-a\"",
            expectedResult = CodeResult(-1)
        )
        test(
            "grep --ignoreCase hello $hellosFilename",
            error = "Internal error: no such option: \"--ignoreCase\"",
            expectedResult = CodeResult(-1)
        )
        test(
            "grep -A -i hey $hellosFilename",
            error = "Internal error: Invalid value for \"-A\": -i is not a valid integer",
            expectedResult = CodeResult(-1)
        )
        test(
            "grep -A -10 hey $hellosFilename",
            error = "Internal error: 'After context' must be non-negative.",
            expectedResult = CodeResult(-1)
        )
        test(
            "grep",
            error = "Internal error: Missing argument \"PATTERN\"",
            expectedResult = CodeResult(-1)
        )
    }

    @Test
    fun `Grep for file without flags`() = withTestContext {
        testFullOutput("grep dolor $engLoremFilename", output = "Lorem ipsum dolor sit amet, consectetur$n")
        testFullOutput("grep \\. $rusLoremFilename", output = "адиписсинг элит.$n")
        testFullOutput("grep \"d.l.. sit\" $engLoremFilename", output = "Lorem ipsum dolor sit amet, consectetur$n")
        testFullOutput("grep . $rusLoremFilename", output = rusLorem)
        test(
            "grep hey $notExistsFilename",
            error = "grep: $notExistsFilename: No such file or directory",
            expectedResult = CodeResult(1)
        )
    }

    @Test
    fun `Grep for file with flags`() = withTestContext {
        testFullOutput("grep -i ЭлИт\\. $rusLoremFilename", output = "адиписсинг элит.$n")

        testFullOutput("grep hello -i $hellosFilename", output = """
            Hello, my friend!
            HeLLo my friend!
            Looong helloooo my friend!
            What the hhello??
            I want to get my helloo back!
            Do you have any hello?
        """.trimIndent() + System.lineSeparator())

        testFullOutput("grep hello -w $hellosFilename", output = "Do you have any hello?$n")

        testFullOutput("grep hello -w -i $hellosFilename", output = """
            Hello, my friend!
            HeLLo my friend!
            Do you have any hello?
        """.trimIndent() + System.lineSeparator())

        testFullOutput("grep hello -A 2 $hellosFilename", output = """
            Looong helloooo my friend!
            What the hhello??
            No such word?
            
            I want to get my helloo back!
            Do you have any hello?
            I do.
            No, I don't.
        """.trimIndent() + System.lineSeparator())

        testFullOutput("grep hello -w $hellosFilename -A 10", output = """
            Do you have any hello?
            I do.
            No, I don't.
        """.trimIndent() + System.lineSeparator())
    }

    @Test
    fun `Grep for input stream`() = withTestContext {
        testFullOutput("echo hey | grep hey", output = "hey$n")
        testFullOutput("echo HeY | grep -i heY", output = "HeY$n")
        testFullOutput("echo 'Hello my friend!' | grep -A 100 -i hell", output = "Hello my friend!$n" )
    }

    @Test
    fun `Grep choose file over stream`() = withTestContext {
        testFullOutput("echo hello | grep -w -A 1 hello $hellosFilename", output = """
            Do you have any hello?
            I do.
        """.trimIndent() + System.lineSeparator())
    }
}

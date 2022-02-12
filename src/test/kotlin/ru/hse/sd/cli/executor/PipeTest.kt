package ru.hse.sd.cli.executor

import org.junit.jupiter.api.Test
import ru.hse.sd.cli.command.CodeResult
import ru.hse.sd.cli.command.ExitResult
import kotlin.test.assertTrue

class PipeTest : CommandExecutorTest() {
    @Test
    fun `Echo and cat`() = withTestContext {
        test("echo | cat", "")
        test("echo 1 | cat", "1")
        test("echo 1 2 3 | cat", "1 2 3")
    }

    @Test
    fun `Echo of the past and the pride of cats`() = withTestContext {
        test("echo Simba | cat | cat", "Simba")
        test("echo Hakuna Matata | cat | cat | cat", "Hakuna Matata")
        test("echo Timon | cat | echo Pumbaa", "Pumbaa")
    }

    @Test
    fun `Lorem and pride of cats`() = withTestContext {
        testFullOutput("cat ${FileContentResources.engLoremFilename} | cat | cat", FileContentResources.engLorem)
    }

    @Test
    fun `Echo and cat from file`() = withTestContext {
        testFullOutput("echo Hey there | cat ${FileContentResources.engLoremFilename}", FileContentResources.engLorem)
    }

    @Test
    fun `Echo and wc`() = withTestContext {
        val echoWcResult = "\t1\t0\t1"
        test("echo | wc", echoWcResult)
        val helloWorld = "Hello world"
        test("echo $helloWorld | wc", "\t1\t2\t${helloWorld.length.withNewLine}")
        test("echo | wc | wc", "\t1\t3\t${echoWcResult.length.withNewLine}")
    }

    @Test
    fun `Intermediate exit`() = withTestContext {
        test("echo Never gonna give you up | exit | echo Never gonna let you down", "Never gonna let you down")
    }

    @Test
    fun `Cat and exit`() = withTestContext {
        test(
            "echo Never gonna give you up | exit | cat ${FileContentResources.notExistsFilename}",
            error = "cat: ${FileContentResources.notExistsFilename}: No such file or directory"
        ) {
            assertTrue { it is CodeResult && it.code != 0 }
        }

        test(
            "echo Never gonna give you up | cat ${FileContentResources.notExistsFilename} | exit",
            expectedResult = ExitResult
        )
    }

    companion object {
        private val Int.withNewLine get() = this + System.lineSeparator().length
    }
}

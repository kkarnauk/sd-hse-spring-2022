package ru.hse.sd.hwproj.runner

import io.mockk.coEvery
import io.mockk.mockkConstructor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.hse.sd.hwproj.model.Checker
import ru.hse.sd.hwproj.model.Homework
import ru.hse.sd.hwproj.model.RunnerTask
import ru.hse.sd.hwproj.model.Submission
import ru.hse.sd.repo.RepositoryFacade
import java.net.URL
import java.sql.Timestamp
import kotlin.io.path.createTempDirectory

class RunnerTest {
    private val tempDirectory = createTempDirectory()

    private fun mockRunner(
        checker: Checker.Content,
        submission: Submission.Content,
        homework: Homework.Content,
        block: (Submission.Result) -> Unit
    ): Runner {
        mockkConstructor(RepositoryFacade::class)
        coEvery { anyConstructed<RepositoryFacade>().getChecker(any()) } answers { checker }
        coEvery { anyConstructed<RepositoryFacade>().getSubmission(any()) } answers { submission }
        coEvery { anyConstructed<RepositoryFacade>().getHomework(any()) } answers { homework }
        coEvery { anyConstructed<RepositoryFacade>().addSubmissionResult(any(), any()) } answers { block(arg(1)) }

        return Runner(tempDirectory)
    }

    private fun now(): Timestamp {
        return Timestamp(System.currentTimeMillis())
    }

    @Test
    fun `test simple runner`() {
        val runner = mockRunner(
            Checker.Content("echo hello\n".toByteArray()),
            Submission.Content(0, now(), URL("https://github.com")),
            Homework.Content("Test homework", now(), now(), "Nothing needed", 0L)
        ) {
            assertEquals(Submission.Result(true, "hello\n", it.checkDate), it)
        }
        runner.run(RunnerTask(0, 0))
    }
}

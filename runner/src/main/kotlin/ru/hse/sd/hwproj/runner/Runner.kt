package ru.hse.sd.hwproj.runner

import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import ru.hse.sd.hwproj.model.Checker
import ru.hse.sd.hwproj.model.Homework
import ru.hse.sd.hwproj.model.RunnerTask
import ru.hse.sd.hwproj.model.Submission
import ru.hse.sd.repo.RepositoryFacade
import java.nio.file.Path
import java.sql.Timestamp
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock
import kotlin.io.path.createDirectories
import kotlin.io.path.exists
import kotlin.io.path.writeBytes

internal class Runner(private val root: Path) {
    private val repository = RepositoryFacade()

    init {
        root.createDirectories()
    }

    fun run(task: RunnerTask) {
        val checkerContent = runBlocking { repository.getChecker(task.checkerId) }
        val path = createChecker(task.checkerId, checkerContent)
        val process = runProcess(path)
        process.putInput(task.submissionId)
        val result = process.waitForResult()
        runBlocking {
            repository.addSubmissionResult(task.submissionId, result)
        }
    }

    private fun createChecker(id: Long, checker: Checker.Content): Path {
        return filesLock.withLock {
            val filepath = root.resolve("checker_$id.sh")
            if (!filepath.exists()) {
                filepath.writeBytes(checker.bytes)
            }
            filepath
        }
    }

    private fun runProcess(path: Path): Process {
        return ProcessBuilder().apply {
            command("bash", path.toString())
        }.start()
    }

    private fun Process.putInput(submissionId: Long) {
        val submission: Submission.Content
        val homework: Homework.Content
        runBlocking {
            submission = repository.getSubmission(submissionId)
            homework = repository.getHomework(submission.homeworkId)
        }
        val input = gson.toJson(Content(submission, homework))
        outputStream.write((input + System.lineSeparator()).toByteArray())
    }

    private fun Process.waitForResult(): Submission.Result {
        waitFor()
        val message = String(inputStream.readAllBytes())
        val code = exitValue()
        return Submission.Result(code == 0, message, Timestamp(System.currentTimeMillis()))
    }

    private data class Content(val submission: Submission.Content, val homework: Homework.Content)

    companion object {
        private val filesLock = ReentrantLock()
        private val gson = GsonBuilder().create()
    }
}

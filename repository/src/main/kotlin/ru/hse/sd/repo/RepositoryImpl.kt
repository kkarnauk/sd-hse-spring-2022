package ru.hse.sd.repo

import org.jetbrains.exposed.sql.insert
import ru.hse.sd.hwproj.model.Checker
import ru.hse.sd.hwproj.model.Homework
import ru.hse.sd.hwproj.model.Submission
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.hse.sd.repo.model.extentions.toHomework
import ru.hse.sd.repo.model.extentions.toHomeworkContent
import ru.hse.sd.repo.model.extentions.toSubmission
import ru.hse.sd.repo.model.extentions.toSubmissionContent
import ru.hse.sd.repo.model.extentions.toSubmissionResult
import ru.hse.sd.repo.schema.Checkers
import ru.hse.sd.repo.schema.Homeworks
import ru.hse.sd.repo.schema.SubmissionResults
import ru.hse.sd.repo.schema.Submissions

internal class RepositoryImpl : Repository {
    private val fileStorage = FileStorage()

    override suspend fun getHomework(id: Int): Homework.Content {
        return transaction {
            Homeworks
                .select { Homeworks.id eq id }
                .single()
                .toHomeworkContent()
        }
    }

    override suspend fun getHomeworks(): List<Homework> {
        return transaction {
            Homeworks.selectAll().map { it.toHomework() }
        }
    }

    override suspend fun addHomework(content: Homework.Content): Homework {
        return transaction {
            val id = Homeworks.insert {
                it[name] = content.name
                it[startDate] = content.startDate.toInstant()
                it[endDate] = content.endDate.toInstant()
                it[statement] = content.statement
                it[checkerId] = content.checkerId
            } get Homeworks.id
            Homework(id, content)
        }
    }

    override suspend fun getSubmission(id: Int): Submission.Content {
        return transaction {
            Submissions
                .select { Submissions.id eq id }
                .single()
                .toSubmissionContent()
        }
    }

    override suspend fun getSubmissions(homeworkId: Int): List<Submission> {
        return transaction {
            Submissions
                .select { Submissions.homeworkId eq homeworkId }
                .map { it.toSubmission() }
        }
    }

    override suspend fun addSubmission(content: Submission.Content): Submission {
        return transaction {
            val id = Submissions.insert {
                it[homeworkId] = content.homeworkId
                it[date] = content.date.toInstant()
                it[link] = content.solutionLink.toString()
            } get Submissions.id
            Submission(id, content)
        }
    }

    override suspend fun getSubmissionResult(submissionId: Int): Submission.Result {
        return transaction {
            SubmissionResults
                .select { SubmissionResults.submissionId eq submissionId }
                .single()
                .toSubmissionResult()
        }
    }

    @Suppress("RemoveRedundantQualifierName")
    override suspend fun addSubmissionResult(submissionId: Int, result: Submission.Result) {
        return transaction {
            SubmissionResults.insert {
                it[SubmissionResults.submissionId] = submissionId
                it[SubmissionResults.success] = result.success
                it[SubmissionResults.message] = result.message
                it[SubmissionResults.checkDate] = result.checkDate.toInstant()
            }
        }
    }

    override suspend fun getChecker(id: Int): Checker.Content {
        return Checker.Content(fileStorage.readBinary(id))
    }

    override suspend fun addChecker(content: Checker.Content): Checker {
        return transaction {
            val id = Checkers.insert { } get Checkers.id
            fileStorage.putBinary(id, content.bytes)
            Checker(id, content)
        }
    }
}

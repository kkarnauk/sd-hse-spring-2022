package ru.hse.sd.repo

import ru.hse.sd.hwproj.model.Checker
import ru.hse.sd.hwproj.model.Homework
import ru.hse.sd.hwproj.model.Submission
import kotlin.random.Random

internal class RepositoryImpl : Repository {
    val list = mutableListOf<Homework.Content>()
    override suspend fun getHomework(id: Long): Homework.Content {
        TODO("Not yet implemented")
    }

    override suspend fun getHomeworks(): List<Homework> {
        return list.map { Homework(Random.nextLong(), it) }
    }

    override suspend fun getSubmission(id: Long): Submission.Content {
        TODO("Not yet implemented")
    }

    override suspend fun getSubmissionResult(submissionId: Long): Submission.Result {
        TODO("Not yet implemented")
    }

    override suspend fun getSubmissions(homeworkId: Long): List<Submission> {
        TODO("Not yet implemented")
    }

    override suspend fun addHomework(content: Homework.Content): Homework {
        list.add(content)
        return Homework(1, content)
    }

    override suspend fun addSubmission(content: Submission.Content): Submission {
        TODO("Not yet implemented")
    }

    override suspend fun addChecker(content: Checker.Content): Checker {
        TODO("Not yet implemented")
    }
}

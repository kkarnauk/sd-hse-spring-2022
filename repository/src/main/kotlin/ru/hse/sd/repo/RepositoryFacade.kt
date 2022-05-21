package ru.hse.sd.repo

import io.ktor.http.*
import ru.hse.sd.hwproj.facade.Facade
import ru.hse.sd.hwproj.model.Checker
import ru.hse.sd.hwproj.model.Homework
import ru.hse.sd.hwproj.model.Submission
import ru.hse.sd.hwproj.settings.RepositorySettings

class RepositoryFacade : Facade(RepositorySettings), Repository {
    override suspend fun getHomework(id: Long): Homework.Content {
        return request("/homework/$id", HttpMethod.Get)
    }

    override suspend fun getHomeworks(): List<Homework> {
        return request("/homework", HttpMethod.Get)
    }

    override suspend fun getSubmission(id: Long): Submission.Content {
        return request("/submission/$id", HttpMethod.Get)
    }

    override suspend fun getSubmissionResult(submissionId: Long): Submission.Result {
        return request("/submission/$submissionId/result", HttpMethod.Get)
    }

    override suspend fun addSubmissionResult(submissionId: Long, result: Submission.Result) {
        return request("/submission/$submissionId/result", HttpMethod.Post, result)
    }

    override suspend fun getSubmissions(homeworkId: Long): List<Submission> {
        return request("/submission", HttpMethod.Get)
    }

    override suspend fun getChecker(id: Long): Checker.Content {
        return request("/check/$id", HttpMethod.Get)
    }

    override suspend fun addHomework(content: Homework.Content): Homework {
        return request("/homework", HttpMethod.Post, content)
    }

    override suspend fun addSubmission(content: Submission.Content): Submission {
        return request("/submission", HttpMethod.Post, content)
    }

    override suspend fun addChecker(content: Checker.Content): Checker {
        return request("/check", HttpMethod.Post, content)
    }
}

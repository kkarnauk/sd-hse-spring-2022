package ru.hse.sd.repo

import io.ktor.client.features.ClientRequestException
import io.ktor.http.HttpMethod
import ru.hse.sd.hwproj.facade.Facade
import ru.hse.sd.hwproj.model.Checker
import ru.hse.sd.hwproj.model.Homework
import ru.hse.sd.hwproj.model.Submission
import ru.hse.sd.hwproj.settings.RepositorySettings

/**
 * Public API for [Repository]. Communicates with actual implementation via REST.
 */
class RepositoryFacade : Facade(RepositorySettings), Repository {
    override suspend fun getHomework(id: Int): Homework.Content {
        return request("/homework/$id", HttpMethod.Get)
    }

    override suspend fun getHomeworks(): List<Homework> {
        return request("/homework", HttpMethod.Get)
    }

    override suspend fun getSubmission(id: Int): Submission.Content {
        return request("/submission/$id", HttpMethod.Get)
    }

    override suspend fun getSubmissionResult(submissionId: Int): Submission.Result? {
        return try {
            request("/submission/$submissionId/result", HttpMethod.Get)
        } catch (_: ClientRequestException) {
            null
        }
    }

    override suspend fun addSubmissionResult(submissionId: Int, result: Submission.Result) {
        return request("/submission/$submissionId/result", HttpMethod.Post, result)
    }

    override suspend fun getSubmissions(homeworkId: Int): List<Submission> {
        return request("/submission/homework/$homeworkId", HttpMethod.Get)
    }

    override suspend fun getChecker(id: Int): Checker.Content {
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

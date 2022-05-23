package ru.hse.sd.webserver

import io.ktor.client.features.ClientRequestException
import io.ktor.http.HttpMethod
import java.net.URL
import java.sql.Timestamp
import ru.hse.sd.hwproj.facade.Facade
import ru.hse.sd.hwproj.model.Checker
import ru.hse.sd.hwproj.model.Homework
import ru.hse.sd.hwproj.model.Submission
import ru.hse.sd.hwproj.settings.WebServerApiSettings

internal object ApiRequest : Facade(WebServerApiSettings) {
    suspend fun getAllHomework(): List<Homework> {
        return request("/api/student/homework", HttpMethod.Get)
    }

    suspend fun addHomework(homeworkContent: Homework.Content): Homework {
        return request("/api/student/homework", HttpMethod.Post, homeworkContent)
    }

    suspend fun getHomework(id: Int): Homework.Content {
        return request("/api/student/homework/${id}", HttpMethod.Get)
    }

    suspend fun getSubmissions(id: Int): List<Submission> {
        return request("/api/student/${id}/submissions", HttpMethod.Get)
    }

    suspend fun getSubmissionResult(submissionId: Int): Submission.Result? {
        return try {
            request("/api/student/submission/$submissionId/result", HttpMethod.Get)
        } catch (e: ClientRequestException) {
            null
        }
    }

    suspend fun addChecker(content: String): Checker {
        return request("/api/submission/checker", HttpMethod.Post, Checker.Content(content.toByteArray().toList()))
    }

    suspend fun addSubmission(homeworkId: Int, link: String) {
        return request(
            "/api/student/submission",
            HttpMethod.Post,
            Submission.Content(homeworkId, Timestamp(System.currentTimeMillis()), URL(link))
        )
    }
}

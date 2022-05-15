package ru.hse.sd.repo

import com.google.gson.GsonBuilder
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.hse.sd.hwproj.model.Checker
import ru.hse.sd.hwproj.model.Homework
import ru.hse.sd.hwproj.model.Submission
import java.net.URL

class RepositoryFacade : Repository {
    private val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }

    private suspend inline fun <reified T> request(url: String, reqMethod: HttpMethod, reqBody: String? = null): T {
        return client.request(URL("http", host, port, url)) {
            method = reqMethod
            reqBody?.let {
                body = it
            }
        }
    }

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

    override suspend fun getSubmissions(homeworkId: Long): List<Submission> {
        return request("/submission", HttpMethod.Get)
    }

    override suspend fun addHomework(content: Homework.Content): Homework {
        return request("/homework", HttpMethod.Post, gson.toJson(content))
    }

    override suspend fun addSubmission(content: Submission.Content): Submission {
        return request("/submission", HttpMethod.Post, gson.toJson(content))
    }

    override suspend fun addChecker(content: Checker.Content): Checker {
        return request("/checker", HttpMethod.Post, gson.toJson(content))
    }

    companion object {
        private val gson = GsonBuilder().create()
    }
}

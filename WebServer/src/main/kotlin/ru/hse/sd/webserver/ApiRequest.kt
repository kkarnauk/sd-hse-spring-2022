package ru.hse.sd.webserver

import io.ktor.http.HttpMethod
import java.net.URL
import java.sql.Timestamp
import kotlin.random.Random
import ru.hse.sd.hwproj.facade.Facade
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

    suspend fun getHomework(id: Int): Homework {
        return request("/api/student/homework/${id}", HttpMethod.Get)
    }

    suspend fun getSubmissions(id: Int): List<Submission> {
        return request("api/student/$id/submissions", HttpMethod.Get) // I'm not sure that' right link
    }
}

package ru.hse.sd.webserver

import com.google.gson.GsonBuilder
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.hse.sd.hwproj.model.Homework
import ru.hse.sd.hwproj.model.RunnerTask
import ru.hse.sd.hwproj.model.Submission
import ru.hse.sd.hwproj.settings.WebServerApiSettings
import java.net.URL
import java.sql.Timestamp
import kotlin.random.Random

private val client = HttpClient(CIO) {
    install(JsonFeature) {
        serializer = GsonSerializer()
    }
}

private suspend inline fun <reified T> request(url: String, reqMethod: HttpMethod, reqBody: String? = null): T {
    return client.request(URL("http", WebServerApiSettings.host, WebServerApiSettings.port, url)) {
        method = reqMethod
        reqBody?.let {
            body = it
        }
    }
}

private val gson = GsonBuilder().create()

// todo remove
val hw = mutableListOf<Homework>()

suspend fun getAllHomework(): List<Homework> {
//     TODO
//    return request("/api/student/homework", HttpMethod.Get)
    return hw
}

suspend fun addHomework(homeworkContent: Homework.Content): Homework {
//     TODO
//    return request("/api/student/homework", HttpMethod.Post, gson.toJson(homeworkContent))
    hw.add(
        Homework(
            Random.nextLong(),
            homeworkContent
        )
    )
    return hw.last()
}

suspend fun getHomework(id: Long): Homework {
//     TODO
//    return request("/api/student/homework/${id}", HttpMethod.Get)
    return hw.single { it.id == id }
}

suspend fun getSubmissions(id: Long): List<Submission> {
//     TODO
//    return request(TODO, HttpMethod.Get)
    return List(Random.nextInt(7)) {
        Submission(
            it.toLong(), RunnerTask(0), Submission.Content(
                Timestamp(Random.nextInt().toLong()),
                URL("https://github.com/submission${Random.nextInt()}")
            )
        )
    }
}
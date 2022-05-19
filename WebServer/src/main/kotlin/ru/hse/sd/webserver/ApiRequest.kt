package ru.hse.sd.webserver

import com.google.gson.GsonBuilder
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.hse.sd.hwproj.model.Homework
import java.net.URL
import kotlin.random.Random

private val client = HttpClient(CIO) {
    install(JsonFeature) {
        serializer = GsonSerializer()
    }
}


private suspend inline fun <reified T> request(url: String, reqMethod: HttpMethod, reqBody: String? = null): T {
    return client.request(URL("http", apiHost, apiPort, url)) {
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
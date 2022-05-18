package ru.hse.sd.webserver

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.hse.sd.hwproj.model.Homework
import ru.hse.sd.hwproj.model.RunnerTask
import java.net.URL
import java.sql.Timestamp

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

suspend fun getAllHomework(): List<Homework> {
    // TODO
//    return request("api/student/homework", HttpMethod.Get)
    return listOf(
        Homework(
            10,
            Homework.Content(
                "Одна домашка",
                Timestamp(0),
                Timestamp(10000000000),
                "statement1 statement1 statement1 statement1 ",
                RunnerTask(1)
            )
        ),
        Homework(
            11,
            Homework.Content(
                "Другая домашка",
                Timestamp(20000000000),
                Timestamp(50000000000),
                "statement2 statement2 statement2",
                RunnerTask(2)
            )
        ),
        Homework(
            12,
            Homework.Content(
                "Ещё домашка",
                Timestamp(20000000000),
                Timestamp(50000000000),
                "statement3 statement3 statement3",
                RunnerTask(2)
            )
        ),
        Homework(
            13,
            Homework.Content(
                "Снова домашка",
                Timestamp(20000000000),
                Timestamp(50000000000),
                "statement3 statement3 statement3",
                RunnerTask(2)
            )
        ),
        Homework(
            13,
            Homework.Content(
                "Снова домашка",
                Timestamp(20000000000),
                Timestamp(50000000000),
                "statement3 statement3 statement3",
                RunnerTask(2)
            )
        ),
        Homework(
            13,
            Homework.Content(
                "Снова домашка",
                Timestamp(20000000000),
                Timestamp(50000000000),
                "statement3 statement3 statement3",
                RunnerTask(2)
            )
        ),
        Homework(
            13,
            Homework.Content(
                "Снова домашка",
                Timestamp(20000000000),
                Timestamp(50000000000),
                "statement3 statement3 statement3",
                RunnerTask(2)
            )
        )
    )
}
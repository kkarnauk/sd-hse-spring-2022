package ru.hse.sd.webserver

import java.net.URL
import java.sql.Timestamp
import kotlin.random.Random
import ru.hse.sd.hwproj.facade.Facade
import ru.hse.sd.hwproj.model.Homework
import ru.hse.sd.hwproj.model.Submission
import ru.hse.sd.hwproj.settings.WebServerApiSettings

internal object ApiRequest : Facade(WebServerApiSettings) {
    // todo remove
    val hw = mutableListOf<Homework>()

    suspend fun getAllHomework(): List<Homework> {
//     TODO
//    return request("/api/student/homework", HttpMethod.Get)
        return hw
    }

    suspend fun addHomework(homeworkContent: Homework.Content): Homework {
//     TODO
//    return request("/api/student/homework", HttpMethod.Post, homeworkContent)
        hw.add(
            Homework(
                Random.nextInt(),
                homeworkContent
            )
        )
        return hw.last()
    }

    suspend fun getHomework(id: Int): Homework {
//     TODO
//    return request("/api/student/homework/${id}", HttpMethod.Get)
        return hw.single { it.id == id }
    }

    suspend fun getSubmissions(id: Int): List<Submission> {
//     TODO
//    return request(TODO, HttpMethod.Get)
        return List(Random.nextInt(7)) {
            Submission(
                it.toInt(), Submission.Content(
                    0,
                    Timestamp(Random.nextInt().toLong()),
                    URL("https://github.com/submission${Random.nextInt()}")
                )
            )
        }
    }
}

package ru.hse.sd.hwproj.queue

import com.google.gson.GsonBuilder
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.hse.sd.hwproj.model.RunnerTask
import ru.hse.sd.hwproj.settings.TasksQueueSettings
import java.net.URL

class TasksQueueFacade : TasksQueue {
    private val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }

    private suspend inline fun <reified T> request(url: String, reqMethod: HttpMethod, reqBody: String? = null): T {
        return client.request(URL("http", TasksQueueSettings.host, TasksQueueSettings.port, url)) {
            method = reqMethod
            reqBody?.let {
                body = it
            }
        }
    }

    override suspend fun sendRunnerTask(task: RunnerTask) {
        return request("/task", HttpMethod.Post, gson.toJson(task))
    }

    companion object {
        private val gson = GsonBuilder().create()
    }
}

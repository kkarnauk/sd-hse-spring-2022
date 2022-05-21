package ru.hse.sd.hwproj.queue

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import ru.hse.sd.hwproj.model.RunnerTask

internal fun Application.configureRouting() {
    install(ContentNegotiation) {
        gson()
    }

    val tasksQueue = TasksSenderImpl()

    routing {
        post("/task") {
            val runnerTask = call.receive<RunnerTask>()
            tasksQueue.sendRunnerTask(runnerTask)
            call.respond(HttpStatusCode.OK)
        }
    }
}

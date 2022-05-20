package ru.hse.sd.hwproj.queue

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.hse.sd.hwproj.settings.TasksQueueSettings

fun main() {
    embeddedServer(Netty, port = TasksQueueSettings.port, host = TasksQueueSettings.host) {
        configureRouting()
    }.start(wait = true)
}

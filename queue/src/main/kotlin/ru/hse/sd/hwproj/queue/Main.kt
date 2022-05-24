package ru.hse.sd.hwproj.queue

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.hse.sd.hwproj.settings.TasksSenderSettings

internal fun main() {
    embeddedServer(Netty, port = TasksSenderSettings.port, host = TasksSenderSettings.host) {
        configureRouting()
    }.start(wait = true)
}

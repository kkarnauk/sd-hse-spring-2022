package ru.hse.sd.hwproj.queue

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.hse.sd.hwproj.settings.QueueHandlerSettings

fun main() {
    embeddedServer(Netty, port = QueueHandlerSettings.port, host = QueueHandlerSettings.host) {
        configureRouting()
    }.start(wait = true)
}

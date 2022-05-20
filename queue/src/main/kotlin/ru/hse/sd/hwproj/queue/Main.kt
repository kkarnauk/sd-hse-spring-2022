package ru.hse.sd.hwproj.queue

import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = queuePort, host = queueHost) {
        configureRouting()
    }.start(wait = true)
}

package ru.hse.sd.repo

import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 10001, host = "0.0.0.0") {
        configureRouting()
    }.start(wait = true)
}

package ru.hse.sd.repo

import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = port, host = host) {
        configureRouting()
    }.start(wait = true)
}

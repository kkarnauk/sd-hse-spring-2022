package ru.hse.sd.webserver

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.hse.sd.hwproj.settings.WebServerSettings

fun main() {
    embeddedServer(Netty, port = WebServerSettings.port, host = WebServerSettings.host) {
        configureRouting()
    }.start(wait = true)
}

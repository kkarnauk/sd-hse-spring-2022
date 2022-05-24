package ru.hse.sd.webserver.api

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.hse.sd.hwproj.settings.WebServerApiSettings

internal fun main() {
    embeddedServer(Netty, port = WebServerApiSettings.port, host = WebServerApiSettings.host) {
        configureRouting()
    }.start(wait = true)
}

package ru.hse.sd.repo

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.hse.sd.hwproj.settings.RepositorySettings

fun main() {
    embeddedServer(Netty, port = RepositorySettings.port, host = RepositorySettings.host) {
        configureRouting()
    }.start(wait = true)
}

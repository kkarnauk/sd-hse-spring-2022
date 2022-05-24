package ru.hse.sd.repo

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.Database
import ru.hse.sd.hwproj.settings.RepositorySettings

private class Main : CliktCommand() {
    val mode: String by option().required()

    override fun run() {
        when (mode) {
            "api" -> {
                embeddedServer(Netty, port = RepositorySettings.port, host = RepositorySettings.host) {
                    configureRouting()
                }.start(wait = true)
            }
            "init" -> initializeDb().also { println("Db initialized") }
            else -> error("unexpected mode $mode")
        }
    }
}

internal fun main(args: Array<String>) {
    Database.connect(
        url = "jdbc:postgresql://db:5432/hwproj",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "postgres"
    )
    Main().main(args)
}

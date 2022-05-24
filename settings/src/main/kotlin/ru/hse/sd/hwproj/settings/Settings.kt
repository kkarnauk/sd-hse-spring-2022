package ru.hse.sd.hwproj.settings

/**
 * Settings for something.
 */
interface Settings

/**
 * Settings to set up host and port.
 */
interface AddressSettings : Settings {
    /**
     * Host to set up.
     */
    val host: String

    /**
     * Port to set up.
     */
    val port: Int
}

/**
 * Settings to set up the tasks queue.
 */
object TasksSenderSettings : AddressSettings {
    override val host: String
        get() = "queue"

    override val port: Int
        get() = 10004
}

/**
 * Settings to set up the broker.
 */
object BrokerSettings : AddressSettings {
    override val host: String
        get() = "rabbit-mq"

    override val port: Int
        get() = 5672

    /**
     * Name of the used queue in the broker.
     */
    const val queueName: String = "runners"
}

/**
 * Settings to set up the repository.
 */
object RepositorySettings : AddressSettings {
    override val host: String
        get() = "repository"

    override val port: Int
        get() = 10001
}

/**
 * Settings to set up the web server.
 */
object WebServerSettings : AddressSettings {
    override val host: String
        get() = "frontend"

    override val port: Int
        get() = 10002
}

/**
 * Settings to set up the web server API.
 */
object WebServerApiSettings : AddressSettings {
    override val host: String
        get() = "backend"

    override val port: Int
        get() = 10003
}

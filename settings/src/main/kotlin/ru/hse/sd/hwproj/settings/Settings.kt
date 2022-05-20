package ru.hse.sd.hwproj.settings

interface Settings

interface AddressSettings : Settings {
    val host: String

    val port: Int
}

object TasksQueueSettings : AddressSettings {
    override val host: String
        get() = "localhost"

    override val port: Int
        get() = 10004
}

object BrokerSettings : AddressSettings {
    override val host: String
        get() = "localhost"

    override val port: Int
        get() = 5672

    const val queueName: String = "runners"
}

object RepositorySettings : AddressSettings {
    override val host: String
        get() = "localhost"

    override val port: Int
        get() = 10001
}

object WebServerSettings : AddressSettings {
    override val host: String
        get() = "localhost"

    override val port: Int
        get() = 10002
}

object WebServerApiSettings : AddressSettings {
    override val host: String
        get() = "localhost"

    override val port: Int
        get() = 10003
}

package ru.hse.sd.hwproj.queue

import com.github.fridujo.rabbitmq.mock.MockConnectionFactory
import com.rabbitmq.client.ConnectionFactory
import io.mockk.every
import io.mockk.mockkConstructor

internal fun mockConnectionFactory(): ConnectionFactory {
    val connectionFactory = MockConnectionFactory()
    mockkConstructor(ConnectionFactory::class)
    every { anyConstructed<ConnectionFactory>().newConnection() } answers { connectionFactory.newConnection() }
    return connectionFactory
}

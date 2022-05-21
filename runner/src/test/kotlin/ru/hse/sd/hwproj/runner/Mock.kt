package ru.hse.sd.hwproj.runner

import com.github.fridujo.rabbitmq.mock.MockConnectionFactory
import com.rabbitmq.client.ConnectionFactory
import io.mockk.every
import io.mockk.mockkConstructor
import ru.hse.sd.hwproj.model.RunnerTask

internal fun mockConnectionFactory(): ConnectionFactory {
    val connectionFactory = MockConnectionFactory()
    mockkConstructor(ConnectionFactory::class)
    every { anyConstructed<ConnectionFactory>().newConnection() } answers { connectionFactory.newConnection() }
    return connectionFactory
}

internal fun mockReceiverRun(block: (RunnerTask) -> Unit): Receiver {
    mockkConstructor(Runner::class)
    every { anyConstructed<Runner>().run(any()) } answers { block(arg(0)) }
    return Receiver()
}

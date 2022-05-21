package ru.hse.sd.hwproj.runner

import com.google.gson.GsonBuilder
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Delivery
import ru.hse.sd.hwproj.model.RunnerTask
import ru.hse.sd.hwproj.settings.BrokerSettings
import java.nio.file.Path

internal class TasksReceiver : AutoCloseable {
    private val runner by lazy {
        Runner(root)
    }

    private val connectionFactory by lazy {
        ConnectionFactory().apply {
            host = BrokerSettings.host
            port = BrokerSettings.port
        }
    }

    private val connection by lazy {
        connectionFactory.newConnection()
    }

    private val channel by lazy {
        connection.createChannel()
    }

    override fun close() {
        channel.close()
        connection.close()
    }

    fun receiveTasks() {
        channel.queueDeclare(BrokerSettings.queueName, false, false, false, null)
        channel.basicConsume(BrokerSettings.queueName, true, ::deliveryCallback) { _ -> }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun deliveryCallback(consumerTag: String, message: Delivery) {
        val task = gson.fromJson(String(message.body), RunnerTask::class.java)
        runner.run(task)
    }

    companion object {
        private val gson = GsonBuilder().create()
        private val root = Path.of("checkers")
    }
}

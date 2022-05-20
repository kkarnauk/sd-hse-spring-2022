package ru.hse.sd.hwproj.runner

import com.google.gson.GsonBuilder
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Delivery
import ru.hse.sd.hwproj.model.RunnerTask
import ru.hse.sd.hwproj.settings.BrokerSettings

internal class Runner {
    fun receiveTasks() {
        val connectionFactory = ConnectionFactory().apply {
            host = BrokerSettings.host
            port = BrokerSettings.port
        }

        connectionFactory.newConnection().use { connection ->
            connection.createChannel().use { channel ->
                channel.queueDeclare(BrokerSettings.queueName, false, false, false, null)
                channel.basicConsume(BrokerSettings.queueName, true, ::deliveryCallback) { _ -> }
            }
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun deliveryCallback(consumerTag: String, message: Delivery) {
        val task = GSON.fromJson(String(message.body), RunnerTask::class.java)
        run(task)
    }

    private fun run(task: RunnerTask) {
        // TODO
    }

    companion object {
        private val GSON = GsonBuilder().create()
    }
}

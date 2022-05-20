package ru.hse.sd.hwproj.queue

import com.google.gson.GsonBuilder
import com.rabbitmq.client.ConnectionFactory
import ru.hse.sd.hwproj.model.RunnerTask
import ru.hse.sd.hwproj.settings.BrokerSettings

internal class TasksQueueImpl : TasksQueue, AutoCloseable {
    private val connectionFactory = ConnectionFactory().apply {
        host = BrokerSettings.host
        port = BrokerSettings.port
    }

    private val connection = connectionFactory.newConnection()
    private val channel = connection.createChannel()

    init {
        channel.queueDeclare(BrokerSettings.queueName, false, false, false, null)
    }

    override fun close() {
        channel.close()
        connection.close()
    }

    override suspend fun sendRunnerTask(task: RunnerTask) {
        channel.basicPublish("", BrokerSettings.queueName, null, gson.toJson(task).toByteArray())
    }

    companion object {
        private val gson = GsonBuilder().create()
    }
}

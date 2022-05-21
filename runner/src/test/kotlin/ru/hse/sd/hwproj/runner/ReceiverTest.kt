package ru.hse.sd.hwproj.runner

import com.google.gson.GsonBuilder
import com.rabbitmq.client.ConnectionFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTimeout
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.hse.sd.hwproj.model.RunnerTask
import ru.hse.sd.hwproj.settings.BrokerSettings
import java.time.Duration
import java.util.LinkedList
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread
import kotlin.concurrent.withLock

class ReceiverTest {
    private lateinit var connectionFactory: ConnectionFactory

    private val receivedMessages = LinkedList<RunnerTask>()
    private val lock = ReentrantLock()
    private val newData = lock.newCondition()

    @BeforeEach
    fun init() {
        connectionFactory = mockConnectionFactory()
        val receiver = mockReceiverRun {
            lock.withLock {
                receivedMessages += it
                newData.signal()
            }
        }

        thread(isDaemon = true) {
            receiver.receiveTasks()
        }
    }

    private fun sendMessage(task: RunnerTask) {
        return connectionFactory.newConnection().use { connection ->
            connection.createChannel().use { channel ->
                channel.queueDeclare(BrokerSettings.queueName, false, false, false, null)
                channel.basicPublish("", BrokerSettings.queueName, null, gson.toJson(task).toByteArray())
            }
        }
    }

    private fun receiveMessage(): RunnerTask {
        lateinit var message: RunnerTask
        assertTimeout(Duration.ofSeconds(1)) {
            lock.withLock {
                while (receivedMessages.isEmpty()) {
                    newData.await()
                }
                message = receivedMessages.removeFirst()
            }
        }
        return message
    }

    @Test
    fun `test consistent send and receive`() {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                val task = RunnerTask(i.toLong(), j.toLong())
                sendMessage(task)
                assertEquals(task, receiveMessage())
            }
        }
    }

    @Test
    fun `test all sends at first`() {
        for (i in 0 until 10) {
            sendMessage(RunnerTask(i.toLong(), i.toLong()))
        }
        for (i in 0 until 10) {
            assertEquals(RunnerTask(i.toLong(), i.toLong()), receiveMessage())
        }
    }

    @Test
    fun `test parallel send and receive`() {
        thread {
            for (i in 0 until 10) {
                sendMessage(RunnerTask(i.toLong(), i.toLong()))
            }
        }
        thread {
            for (i in 0 until 10) {
                assertEquals(RunnerTask(i.toLong(), i.toLong()), receiveMessage())
            }
        }.join()
    }

    companion object {
        private val gson = GsonBuilder().create()
    }
}

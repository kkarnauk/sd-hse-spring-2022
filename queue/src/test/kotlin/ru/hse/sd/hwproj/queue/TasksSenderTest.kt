package ru.hse.sd.hwproj.queue

import com.google.gson.GsonBuilder
import com.rabbitmq.client.ConnectionFactory
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTimeout
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import ru.hse.sd.hwproj.model.RunnerTask
import ru.hse.sd.hwproj.settings.BrokerSettings
import java.time.Duration
import kotlin.concurrent.thread

class TasksSenderTest {
    private lateinit var connectionFactory: ConnectionFactory

    @BeforeEach
    fun init() {
        connectionFactory = mockConnectionFactory()
    }

    private fun receiveMessage(): RunnerTask? {
        return connectionFactory.newConnection().use { connection ->
            connection.createChannel().use { channel ->
                channel.queueDeclare(BrokerSettings.queueName, false, false, false, null)
                channel.basicGet(BrokerSettings.queueName, true)?.body?.let { message ->
                    gson.fromJson(String(message), RunnerTask::class.java)
                }
            }
        }
    }

    @ParameterizedTest
    @MethodSource("implementations")
    fun `test consistent send and receive`(queue: TasksSender) = runBlocking {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                val task = RunnerTask(i, j)
                queue.sendRunnerTask(task)
                assertEquals(task, receiveMessage())
            }
        }
    }

    @ParameterizedTest
    @MethodSource("implementations")
    fun `test all sends at first`(queue: TasksSender) = runBlocking {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                queue.sendRunnerTask(RunnerTask(i, j))
            }
        }
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                assertEquals(RunnerTask(i, j), receiveMessage())
            }
        }
    }

    @ParameterizedTest
    @MethodSource("implementations")
    fun `test parallel send and receive`(queue: TasksSender) {
        val count = 12

        @Suppress("BlockingMethodInNonBlockingContext")
        thread {
            runBlocking {
                for (i in 0 until count) {
                    queue.sendRunnerTask(RunnerTask(i, 0))
                    Thread.sleep(100)
                }
            }
        }
        thread {
            for (i in 0 until count) {
                assertTimeout(Duration.ofSeconds(2)) {
                    var task: RunnerTask? = null
                    while (task == null) {
                        task = receiveMessage()
                    }
                    assertEquals(RunnerTask(i, 0), task)
                }
            }
        }.join()
    }

    companion object {
        @JvmStatic
        fun implementations() = listOf<TasksSender>(TasksSenderImpl())

        private val gson = GsonBuilder().create()
    }
}

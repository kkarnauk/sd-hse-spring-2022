package ru.hse.sd.hwproj.queue

import ru.hse.sd.hwproj.model.RunnerTask

/**
 * Handles requests to perform a check.
 */
interface TasksSender {
    /**
     * Sends [task] to a queue in order to perform a check.
     */
    suspend fun sendRunnerTask(task: RunnerTask)
}

package ru.hse.sd.hwproj.queue

import ru.hse.sd.hwproj.model.RunnerTask

interface QueueHandler {
    fun sendRunnerTask(task: RunnerTask)
}

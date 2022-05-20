package ru.hse.sd.hwproj.queue

import ru.hse.sd.hwproj.model.RunnerTask

interface TasksQueue {
    suspend fun sendRunnerTask(task: RunnerTask)
}

package ru.hse.sd.hwproj.queue

import ru.hse.sd.hwproj.model.RunnerTask

interface TasksQueue {
    fun sendRunnerTask(task: RunnerTask)
}

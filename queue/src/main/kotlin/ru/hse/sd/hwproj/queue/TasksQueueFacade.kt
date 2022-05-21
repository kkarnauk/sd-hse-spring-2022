package ru.hse.sd.hwproj.queue

import io.ktor.http.*
import ru.hse.sd.hwproj.facade.Facade
import ru.hse.sd.hwproj.model.RunnerTask
import ru.hse.sd.hwproj.settings.TasksQueueSettings

class TasksQueueFacade : Facade(TasksQueueSettings), TasksQueue {
    override suspend fun sendRunnerTask(task: RunnerTask) {
        return request("/task", HttpMethod.Post, task)
    }
}

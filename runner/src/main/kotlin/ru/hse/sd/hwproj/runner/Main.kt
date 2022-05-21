package ru.hse.sd.hwproj.runner

import kotlin.concurrent.thread

private fun startRunner() = thread(start = true, isDaemon = false) {
    TasksReceiver().receiveTasks()
}

internal fun main() {
    startRunner()
    startRunner()
    startRunner()
}

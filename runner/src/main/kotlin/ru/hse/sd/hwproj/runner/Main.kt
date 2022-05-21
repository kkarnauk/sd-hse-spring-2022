package ru.hse.sd.hwproj.runner

import kotlin.concurrent.thread

private fun startRunner() = thread(start = true, isDaemon = false) {
    Receiver().receiveTasks()
}

fun main() {
    startRunner()
    startRunner()
    startRunner()
}

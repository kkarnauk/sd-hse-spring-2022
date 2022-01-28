package ru.hse.sd.cli

import ru.hse.sd.cli.command.CommandExecutor
import ru.hse.sd.cli.command.ExitResult

private const val commandLinePrefix = ":> "

fun main() {
    val executor = CommandExecutor(System.`in`, System.out, System.err)
    while (true) {
        print(commandLinePrefix)
        val command = readLine()
        if (command != null) {
            val result = executor.execute(command)
            if (result is ExitResult) {
                break
            }
        } else {
            println("Couldn't read a command.")
        }
    }
}

package ru.hse.sd.cli

import ru.hse.sd.cli.command.CommandExecutor
import ru.hse.sd.cli.command.ExitResult
import java.io.InputStream

private const val commandLinePrefix = ":> "

fun main() {
    val executor = CommandExecutor(InputStream.nullInputStream(), System.out, System.err)
    while (true) {
        print(commandLinePrefix)
        try {
            val command = readLine()
            if (command != null) {
                val result = executor.execute(command)
                if (result is ExitResult) {
                    break
                }
            } else {
                println("Couldn't read a command.")
            }
        } catch (e: Throwable) {
            println(e.message)
            println("\nPlease, try again!")
        }
    }
}

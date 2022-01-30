package ru.hse.sd.cli.command

import ru.hse.sd.cli.env.Environment
import ru.hse.sd.cli.env.IoContext
import ru.hse.sd.cli.util.write
import java.io.File
import java.io.OutputStream
import java.nio.file.Path

sealed class Command {
    open val name: String by lazy {
        this::class.simpleName?.removeSurrounding(prefix = "", suffix = "Command")?.lowercase() ?: "unknown command"
    }

    abstract fun execute(context: IoContext, env: Environment): CommandResult

    protected fun withValidatingFile(file: File, error: OutputStream, onSuccess: () -> CodeResult): CodeResult {
        return if (!file.isFile) {
            error.write("$name: ${file.name}: Is not a file${System.lineSeparator()}")
            CodeResult(1)
        } else if (!file.exists()) {
            error.write("$name: ${file.name}: No such file or directory${System.lineSeparator()}")
            CodeResult(1)
        } else {
            onSuccess()
        }
    }
}

data class EchoCommand(val args: List<String>) : Command() {
    override fun execute(context: IoContext, env: Environment): CommandResult {
        val message = args.joinToString(separator = " ", postfix = System.lineSeparator())
        context.output.write(message)
        return CodeResult.success
    }
}

data class CatCommand(val argument: String?) : Command() {
    override fun execute(context: IoContext, env: Environment): CommandResult {
        return if (argument != null) {
            val file = env.resolvePath(Path.of(argument)).toFile()
            withValidatingFile(file, context.error) {
                context.output.write(file.readBytes())
                CodeResult.success
            }
        } else {
            context.output.write(context.input.readAllBytes())
            CodeResult.success
        }
    }
}

data class WcCommand(val argument: String?) : Command() {
    private data class WcResult(
        val linesCount: Int,
        val wordsCount: Int,
        val symbolsCount: Int
    ) {
        override fun toString(): String = "\t$linesCount\t$wordsCount\t$symbolsCount"
    }

    private val whitespaceRegex: Regex = Regex("\\s")

    private fun wc(text: String): WcResult = WcResult(
        text.lines().size,
        text.split(whitespaceRegex).filterNot { it.isEmpty() }.size,
        text.length
    )

    override fun execute(context: IoContext, env: Environment): CommandResult {
        // TODO copy-paste
        return if (argument != null) {
            val file = env.resolvePath(Path.of(argument)).toFile()
            withValidatingFile(file, context.error) {
                val wcResult = wc(file.readText())
                context.output.write(wcResult.toString())
                context.output.write(System.lineSeparator())
                CodeResult.success
            }
        } else {
            val wcResult = wc(String(context.input.readAllBytes()))
            context.output.write(wcResult.toString())
            context.output.write(System.lineSeparator())
            CodeResult.success
        }
    }
}

object PwdCommand : Command() {
    override fun execute(context: IoContext, env: Environment): CommandResult {
        context.output.write(env.workingDirectory.toString() + System.lineSeparator())
        return CodeResult.success
    }
}

object ExitCommand : Command() {
    override fun execute(context: IoContext, env: Environment): CommandResult = ExitResult
}

data class ExternalCommand(override val name: String) : Command() {
    override fun execute(context: IoContext, env: Environment): CommandResult = TODO()
}

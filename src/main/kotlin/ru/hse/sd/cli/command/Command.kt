package ru.hse.sd.cli.command

import ru.hse.sd.cli.env.Environment
import ru.hse.sd.cli.env.IoContext
import ru.hse.sd.cli.util.write
import java.io.File
import java.io.OutputStream
import java.nio.file.Path

/**
 * Represents a command in CLI that can be executed by [execute].
 * Each command has its own unique [name].
 */
sealed class Command {
    /**
     * Name of the command.
     */
    open val name: String by lazy {
        this::class.simpleName?.removeSurrounding(prefix = "", suffix = "Command")?.lowercase() ?: "unknown command"
    }

    /**
     * Main method that executes the command with provided [context] and [env].
     * @param context input/output/error streams that used while executing.
     * @param env execution environment that used to get variables and current working directory.
     */
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

/**
 * Represents the `echo`-command in CLI.
 * Takes a message from [args] and sends it to output, ignoring input.
 */
data class EchoCommand(
    /**
     * Strings to be printed to output, separated by a space.
     */
    val args: List<String>
) : Command() {
    override fun execute(context: IoContext, env: Environment): CommandResult {
        val message = args.joinToString(separator = " ", postfix = System.lineSeparator())
        context.output.write(message)
        return CodeResult.success
    }
}

/**
 * Represents the `cat`-command in CLI.
 * Takes a file as [argument] or a message from input and sends it to output.
 */
data class CatCommand(
    /**
     * Possible name of the file, contents of which are printed to output.
     * If the file is not provided, then input is used instead.
     */
    val argument: String?
) : Command() {
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

/**
 * Represents the `wc`-command in CLI.
 * Takes a file as [argument] or a message from input and counts lines, words and symbols in it.
 */
data class WcCommand(
    /**
     * Possible name of the file that used to count lines, words and symbols in it.
     * All this information is sent to output.
     * If the file is not provided, then input is used instead.
     */
    val argument: String?
) : Command() {
    private data class WcResult(
        val linesCount: Int,
        val wordsCount: Int,
        val byteCount: Int
    ) {
        override fun toString(): String = "\t$linesCount\t$wordsCount\t$byteCount"
    }

    private val whitespaceRegex: Regex = Regex("\\s")

    private fun wc(bytes: ByteArray): WcResult {
        val separator = System.lineSeparator()
        return WcResult(
            bytes.asSequence().windowed(size = separator.length, step = 1).count { it.string == separator },
            String(bytes).split(whitespaceRegex).filterNot { it.isEmpty() }.size,
            bytes.size
        )
    }

    private val List<Byte>.string get() = joinToString("") { it.toInt().toChar().toString() }

    override fun execute(context: IoContext, env: Environment): CommandResult {
        return if (argument != null) {
            val file = env.resolvePath(Path.of(argument)).toFile()
            withValidatingFile(file, context.error) {
                val wcResult = wc(file.readBytes())
                context.output.write(wcResult.toString())
                context.output.write(System.lineSeparator())
                CodeResult.success
            }
        } else {
            val wcResult = wc(context.input.readAllBytes())
            context.output.write(wcResult.toString())
            context.output.write(System.lineSeparator())
            CodeResult.success
        }
    }
}

/**
 * Represents the `pwd`-command in CLI.
 * Prints the current working directory to output.
 */
object PwdCommand : Command() {
    override fun execute(context: IoContext, env: Environment): CommandResult {
        context.output.write(env.workingDirectory.toString() + System.lineSeparator())
        return CodeResult.success
    }
}

/**
 * Represents the `exit`-command in CLI.
 * Exists CLI.
 */
object ExitCommand : Command() {
    override fun execute(context: IoContext, env: Environment): CommandResult = ExitResult
}

/**
 * Represents all other external commands in CLI.
 * It tries to invoke an external command, accessible from the terminal, with [name] and [args].
 */
data class ExternalCommand(
    /**
     * Name of the process to be executed.
     */
    override val name: String,
    /**
     * Arguments to be used running the process.
     */
    val args: List<String>
) : Command() {
    override fun execute(context: IoContext, env: Environment): CommandResult {
        val process = ProcessBuilder().apply {
            command(name, *args.toTypedArray())
            environment() += env.variables
        }.start()
        context.input.transferTo(process.outputStream)
        process.waitFor()
        process.inputStream.transferTo(context.output)
        process.errorStream.transferTo(context.error)
        return CodeResult(process.exitValue())
    }
}

/**
 * Represents command that does nothing, usually represents empty line in cli
 * Does not do anything with neither input, output nor error stream
 */
object EmptyCommand : Command() {
    override fun execute(context: IoContext, env: Environment): CommandResult = CodeResult.success
}

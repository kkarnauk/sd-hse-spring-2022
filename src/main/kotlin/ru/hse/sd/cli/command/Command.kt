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
abstract class Command {
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

    protected fun openAndValidateFile(
        filename: String,
        error: OutputStream,
        env: Environment,
        onSuccess: (File) -> CodeResult
    ): CodeResult {
        val file = env.resolvePath(Path.of(filename)).toFile()
        return if (!file.exists()) {
            error.write("$name: ${filename}: No such file or directory${System.lineSeparator()}")
            CodeResult(1)
        } else if (!file.isFile) {
            error.write("$name: ${filename}: Is not a file${System.lineSeparator()}")
            CodeResult(1)
        } else {
            onSuccess(file)
        }
    }
}

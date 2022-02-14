package ru.hse.sd.cli.command.impl

import ru.hse.sd.cli.command.CodeResult
import ru.hse.sd.cli.command.Command
import ru.hse.sd.cli.command.CommandResult
import ru.hse.sd.cli.env.Environment
import ru.hse.sd.cli.env.IoContext

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
            openAndValidateFile(argument, context.error, env, { CodeResult(1) }) { file ->
                context.output.write(file.readBytes())
                CodeResult.success
            }
        } else {
            context.output.write(context.input.readAllBytes())
            CodeResult.success
        }
    }
}

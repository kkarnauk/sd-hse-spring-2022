package ru.hse.sd.cli.command.impl

import ru.hse.sd.cli.command.CodeResult
import ru.hse.sd.cli.command.Command
import ru.hse.sd.cli.command.CommandResult
import ru.hse.sd.cli.env.Environment
import ru.hse.sd.cli.env.IoContext

/**
 * Represents the command that assign [varValue] to the [varName] in the [Environment]
 */
data class AssignmentCommand(
    /**
     * Name of the variable
     */
    val varName: String,
    /**
     * Content to assign
     */
    val varValue: String
) : Command() {
    override fun execute(context: IoContext, env: Environment): CommandResult {
        env.putVariable(varName, varValue)
        return CodeResult.success
    }
}

package ru.hse.sd.cli.command

/**
 * Result of executing a [Command].
 */
sealed interface CommandResult

/**
 * Result meaning that we should end executing commands. Used in [ExitCommand].
 */
object ExitResult : CommandResult

/**
 * Regular result with the return code.
 */
data class CodeResult(
    /**
     * The return code of the executed command.
     */
    val code: Int
) : CommandResult {
    companion object {
        /**
         * Result code meaning that a command is successfully executed.
         */
        val success = CodeResult(0)

        /**
         * Result code meaning that there is an internal error that cannot be handled.
         */
        val internalError = CodeResult(-1)
    }
}

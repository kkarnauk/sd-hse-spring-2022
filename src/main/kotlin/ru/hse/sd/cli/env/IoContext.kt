package ru.hse.sd.cli.env

import java.io.InputStream
import java.io.OutputStream

/**
 * Represents all streams that used by commands while executing.
 */
data class IoContext(
    /**
     * Input for a command.
     */
    val input: InputStream,
    /**
     * Output of a command.
     */
    val output: OutputStream,
    /**
     * Errors of a command.
     */
    val error: OutputStream
)

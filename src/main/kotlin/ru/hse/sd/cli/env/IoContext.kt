package ru.hse.sd.cli.env

import java.io.InputStream
import java.io.OutputStream

data class IoContext(
    val input: InputStream,
    val output: OutputStream,
    val error: OutputStream
)

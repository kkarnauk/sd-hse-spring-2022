package ru.hse.sd.cli.util

import java.io.OutputStream

/**
 * Writes [str] into [this]
 */
fun OutputStream.write(str: String) = write(str.toByteArray())

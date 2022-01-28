package ru.hse.sd.cli.util

import java.io.OutputStream

fun OutputStream.write(str: String) = write(str.toByteArray())

package ru.hse.sd.repo

import java.nio.file.Paths
import kotlin.io.path.createDirectories
import kotlin.io.path.createFile
import kotlin.io.path.inputStream
import kotlin.io.path.outputStream

internal class FileStorage {
    private val binaries = Paths.get("cwd", "binaries")
        .also { it.createDirectories() }

    fun putBinary(checkerId: Int, bytes: ByteArray): String {
        val path = binaries
            .resolve(checkerId.toString())
            .also { it.createFile() }
        path.outputStream().use {
            it.write(bytes)
        }
        return path.toAbsolutePath().toString()
    }

    fun readBinary(checkerId: Int): ByteArray {
        binaries.resolve(checkerId.toString()).inputStream().use {
            return it.readAllBytes()
        }
    }
}

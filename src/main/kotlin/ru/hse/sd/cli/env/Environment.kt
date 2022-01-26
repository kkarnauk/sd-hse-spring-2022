package ru.hse.sd.cli.env

import java.nio.file.Path

class Environment {
    val workingDirectory: Path
        get() = TODO()

    fun getVariable(name: String): String = TODO()

    fun putVariable(name: String, value: String) {
        TODO()
    }

    operator fun get(variableName: String): String = getVariable(variableName)

    operator fun set(variableName: String, variableValue: String) = putVariable(variableName, variableValue)
}

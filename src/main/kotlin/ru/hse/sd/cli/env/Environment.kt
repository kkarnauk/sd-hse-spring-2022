package ru.hse.sd.cli.env

import java.nio.file.Path

class Environment {
    private val variables = mutableMapOf<String, String>()

    val workingDirectory: Path
        get() = Path.of("/").toAbsolutePath()

    fun resolvePath(path: Path): Path = if (path.isAbsolute) path else workingDirectory.resolve(path)

    fun getVariable(name: String): String = variables.getOrDefault(name, "")

    fun putVariable(name: String, value: String) {
        variables[name] = value
    }

    operator fun get(variableName: String): String = getVariable(variableName)

    operator fun set(variableName: String, variableValue: String) = putVariable(variableName, variableValue)
}

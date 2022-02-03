package ru.hse.sd.cli.env

import java.nio.file.Path

class Environment {
    private val myVariables = mutableMapOf<String, String>()

    val workingDirectory: Path
        get() = Path.of("").toAbsolutePath()

    val variables: Map<String, String>
        get() = myVariables

    fun resolvePath(path: Path): Path = if (path.isAbsolute) path else workingDirectory.resolve(path)

    fun getVariable(name: String): String = myVariables.getOrDefault(name, "")

    fun putVariable(name: String, value: String) {
        myVariables[name] = value
    }

    operator fun get(variableName: String): String = getVariable(variableName)

    operator fun set(variableName: String, variableValue: String) = putVariable(variableName, variableValue)
}

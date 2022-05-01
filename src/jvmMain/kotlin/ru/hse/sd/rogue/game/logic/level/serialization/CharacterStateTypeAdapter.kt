package ru.hse.sd.rogue.game.logic.level.serialization

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import ru.hse.sd.rogue.game.state.character.CharacterState

class CharacterStateTypeAdapter(
    private val gson: Gson,
    private val delegates: Map<String, TypeAdapter<CharacterState>>
) : TypeAdapter<CharacterState>() {
    override fun write(out: JsonWriter, value: CharacterState) {
        out.obj {
            val name = value.javaClass.name
            name("fullyQualifiedName")
            gson.toJson(gson.toJsonTree(name), out)
            name("value")
            delegates[name]!!.write(out, value)
        }
    }

    override fun read(inn: JsonReader): CharacterState {
        return inn.obj {
            nextName().also { require(it == "fullyQualifiedName") { it } }
            val fullyQualifiedName = nextString()
            nextName().also { require(it == "value") }
            val delegate = delegates[fullyQualifiedName]!!
            delegate.read(inn)
        }
    }

    private fun <T> JsonWriter.obj(block: JsonWriter.() -> T): T {
        beginObject()
        val t = block()
        endObject()
        return t
    }

    private fun <T> JsonReader.obj(block: JsonReader.() -> T): T {
        beginObject()
        val t = block()
        endObject()
        return t
    }
}

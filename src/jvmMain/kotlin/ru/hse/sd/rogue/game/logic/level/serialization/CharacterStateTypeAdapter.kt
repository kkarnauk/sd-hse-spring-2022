package ru.hse.sd.rogue.game.logic.level.serialization

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import ru.hse.sd.rogue.game.state.character.CharacterState

class CharacterStateTypeAdapter(
    private val gson: Gson,
    private val delegate: TypeAdapter<CharacterState>
) : TypeAdapter<CharacterState>() {
    override fun write(out: JsonWriter, value: CharacterState) {
        out.obj {
            name("fullyQualifiedName")
            gson.toJson(gson.toJsonTree(value.javaClass.name), out)
            name("value")
            delegate.write(out, value)
        }
    }

    override fun read(inn: JsonReader): CharacterState {
        return inn.obj {
            nextName()
            val fullyQualifiedName = nextString()
            nextName()
            gson.fromJson(inn, Class.forName(fullyQualifiedName))
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

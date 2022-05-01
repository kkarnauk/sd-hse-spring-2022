package ru.hse.sd.rogue.game.logic.level.serialization

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.Reader
import java.io.Writer
import ru.hse.sd.rogue.game.logic.level.Level

object LevelJsonSerializer {
    private val gson: Gson = GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapterFactory(LevelTypeAdapterFactory())
        .create()

    fun Level.writeTo(writer: Writer) {
        gson.toJson(this, writer)
    }

    fun readFrom(reader: Reader): Level {
        return gson.fromJson(reader, Level::class.java)
    }
}

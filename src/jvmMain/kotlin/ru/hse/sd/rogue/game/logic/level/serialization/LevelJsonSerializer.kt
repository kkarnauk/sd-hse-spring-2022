package ru.hse.sd.rogue.game.logic.level.serialization

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.nio.file.Path
import kotlin.io.path.bufferedReader
import kotlin.io.path.bufferedWriter
import ru.hse.sd.rogue.game.logic.level.Level

object LevelJsonSerializer {
    private val gson: Gson = GsonBuilder()
        .registerTypeAdapterFactory(LevelTypeAdapterFactory())
        .create()

    fun Level.writeTo(path: Path) {
        path.bufferedWriter().use {
            gson.toJson(this, it)
        }
    }

    fun readFrom(path: Path): Level {
        return path.bufferedReader().use {
            gson.fromJson(it, Level::class.java)
        }
    }
}

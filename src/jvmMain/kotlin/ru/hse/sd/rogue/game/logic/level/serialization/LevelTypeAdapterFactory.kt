package ru.hse.sd.rogue.game.logic.level.serialization

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.soywiz.korio.util.isSubtypeOf
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.character.CharacterMutableState
import kotlin.reflect.KClass

class LevelTypeAdapterFactory : TypeAdapterFactory {
    override fun <T> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {
        @Suppress("UNCHECKED_CAST") // java moment
        return when {
            type.rawType.isSubtypeOf(CharacterMutableState::class.java) ->
                CharacterStateTypeAdapter(
                    gson,
                    CharacterMutableState::class.deepSealedSubclasses.associate {
                        val delegate = gson.getDelegateAdapter(this, TypeToken.get(it.java))
                        it.qualifiedName!! to delegate as TypeAdapter<CharacterMutableState>
                    }
                ) as TypeAdapter<T>
            type.rawType === MutablePosition::class.java -> {
                gson.getAdapter<Position>().applyAfter { asMutable() } as TypeAdapter<T>
            }
            else -> null
        }
    }
}

private fun <T : Any, U : T> TypeAdapter<T>.applyAfter(transform: T.() -> U) = object : TypeAdapter<U>() {
    override fun write(out: JsonWriter, value: U) {
        this@applyAfter.write(out, value)
    }

    override fun read(inn: JsonReader): U {
        return this@applyAfter.read(inn).transform()
    }
}

private inline fun <reified T> Gson.getAdapter(): TypeAdapter<T> = getAdapter(T::class.java)

private val <T : Any> KClass<T>.deepSealedSubclasses: List<KClass<out T>> get() =
    sealedSubclasses.flatMap {
        if (it.isSealed) {
            it.deepSealedSubclasses
        } else {
            listOf(it)
        }
    }

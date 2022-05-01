package ru.hse.sd.rogue.game.logic.level.serialization

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.soywiz.korio.util.isSubtypeOf
import kotlin.reflect.KClass
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.character.CharacterState

class LevelTypeAdapterFactory : TypeAdapterFactory {
    override fun <T> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {
        @Suppress("UNCHECKED_CAST") // java moment
        return when {
            type.rawType.isSubtypeOf(CharacterState::class.java) ->
                CharacterStateTypeAdapter(
                    gson,
                    CharacterState::class.deepSealedSubclasses.associate {
                        val delegate = gson.getDelegateAdapter(this, TypeToken.get(it.java))
                        it.qualifiedName!! to delegate as TypeAdapter<CharacterState>
                    }
                ) as TypeAdapter<T>
            type.rawType === MutablePosition::class.java ->
                MutablePositionTypeAdapter(gson.getAdapter(Position::class.java)) as TypeAdapter<T>
            else -> null
        }
    }
}

private val <T : Any> KClass<T>.deepSealedSubclasses: List<KClass<out T>> get() =
    sealedSubclasses.flatMap {
        if (it.isSealed) {
            it.deepSealedSubclasses
        } else {
            listOf(it)
        }
    }

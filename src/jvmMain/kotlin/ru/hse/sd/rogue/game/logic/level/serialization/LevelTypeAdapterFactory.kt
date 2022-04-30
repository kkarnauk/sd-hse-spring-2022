package ru.hse.sd.rogue.game.logic.level.serialization

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.soywiz.korio.util.isSubtypeOf
import ru.hse.sd.rogue.game.state.character.CharacterState

class LevelTypeAdapterFactory : TypeAdapterFactory {
    override fun <T : Any?> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {
        @Suppress("UNCHECKED_CAST") // java moment
        return when {
            type.rawType.isSubtypeOf(CharacterState::class.java) ->
                CharacterStateTypeAdapter(
                    gson,
                    gson.getDelegateAdapter(this, type) as TypeAdapter<CharacterState>
                ) as TypeAdapter<T>
            else -> null
        }
    }
}
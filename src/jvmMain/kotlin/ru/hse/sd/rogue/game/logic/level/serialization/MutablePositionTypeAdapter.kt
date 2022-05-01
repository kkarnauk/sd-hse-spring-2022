package ru.hse.sd.rogue.game.logic.level.serialization

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.logic.position.Position

class MutablePositionTypeAdapter(private val delegate: TypeAdapter<Position>) : TypeAdapter<MutablePosition>() {
    override fun write(out: JsonWriter, value: MutablePosition) {
        delegate.write(out, value)
    }

    override fun read(inn: JsonReader): MutablePosition {
        return delegate.read(inn).asMutable()
    }
}

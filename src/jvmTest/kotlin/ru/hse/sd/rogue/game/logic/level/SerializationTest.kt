package ru.hse.sd.rogue.game.logic.level

import java.io.StringReader
import java.io.StringWriter
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import ru.hse.sd.rogue.game.logic.level.mobsfabric.AnimalMobsFactory
import ru.hse.sd.rogue.game.logic.level.mobsfabric.ClassicDungeonMobsFactory
import ru.hse.sd.rogue.game.logic.level.serialization.LevelJsonSerializer
import ru.hse.sd.rogue.mapSize


class SerializationTest {
    @Test
    fun `test level serializes and deserializes correctly`() = levels.forEach { expected ->
        with(LevelJsonSerializer) {
            val actual = with(StringWriter()) {
                expected.writeTo(this)
                readFrom(StringReader(toString()))
            }
            assertEqualsByFields(expected, actual)
        }
    }

    private val levels get() = listOf(3, 4, 5, 6).map {
        level {
            generate {
                map.width = mapSize.width
                map.height = mapSize.height
                map.border = 1
                map.corridorThickness = 0 to 1
                map.corridorWobbling = 0.05
                map.minRoomSize = 7
                map.splitNumIterations = it
                mobs.mobsFactory = listOf(ClassicDungeonMobsFactory(), AnimalMobsFactory())[it % 2]
                random = Random(1234)
            }
        }
    }

    private fun <T : Any> assertEqualsByFields(expected: T, actual: T) {
        assertEquals(expected::class, actual::class, "Types are expected to be the same")
        val clazz = expected::class.java
        clazz.fields.forEach {
            val expectedValue = it.get(expected)
            val actualValue = it.get(actual)
            if (it.type.isPrimitive) {
                assertEquals(expectedValue, actualValue)
            } else {
                assertEqualsByFields(expectedValue, actualValue)
            }
        }
    }
}

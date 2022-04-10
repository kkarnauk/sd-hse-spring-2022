package ru.hse.sd.rogue.game.logic.characteristics

import org.junit.Test
import kotlin.test.assertTrue

internal class DamageTest {

    @Test
    fun testValue() {
        val min = 10
        val max = 30
        val damage = Damage(min, max)
        repeat(100) {
            val value = damage.value
            assertTrue(min <= value)
            assertTrue(max >= value)
        }
    }
}
package ru.hse.sd.rogue.game.logic.action

import com.soywiz.korge.view.Container
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals


internal class ActionsManagerTest {

    @Test
    fun simpleTest() {
        val list = mutableListOf<Int>()
        with(ActionsManager(Container(), 30)) {
            start()
            register(ActionPriority.Low, IrreversibleAction { list.add(5) })
            register(ActionPriority.High, IrreversibleAction { list.add(0) })
            register(ActionPriority.Normal, IrreversibleAction { list.add(3) })
            manualInvoke()
        }
        assertEquals(listOf(5, 3, 0), list)
    }

    @Test
    fun withFrequencyModifierTest() {
        var x1 = 0
        var x2 = 0
        with(ActionsManager(Container(), 30)) {
            start()
            registerRepeatable(ActionPriority.Normal, IrreversibleAction { x1++ }.withFrequency(3))
            registerRepeatable(ActionPriority.Normal, IrreversibleAction { x2++ }.withFrequency(5))
            for (i in 0..15) {
                assertEquals(i / 3, x1)
                assertEquals(i / 5, x2)
                manualInvoke()
            }
        }
    }

    @Test
    fun probablyModifierTest() {
        val random = Random(1337)
        val list = mutableListOf<Int>()
        var x = 0
        with(ActionsManager(Container(), 30)) {
            start()
            registerRepeatable(ActionPriority.Normal, IrreversibleAction { list.add(x); x++ }.probably(0.5, random))
            manualInvoke()
            assertEquals(listOf(), list)
            manualInvoke()
            assertEquals(listOf(), list)
            manualInvoke()
            assertEquals(listOf(0), list)
            manualInvoke()
            assertEquals(listOf(0, 1), list)
            manualInvoke()
            assertEquals(listOf(0, 1), list)
            manualInvoke()
            assertEquals(listOf(0, 1), list)
            manualInvoke()
            assertEquals(listOf(0, 1), list)
            manualInvoke()
            assertEquals(listOf(0, 1), list)
            manualInvoke()
            assertEquals(listOf(0, 1, 2), list)
        }
    }
}
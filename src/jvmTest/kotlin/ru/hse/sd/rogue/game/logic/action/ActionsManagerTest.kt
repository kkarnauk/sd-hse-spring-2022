package ru.hse.sd.rogue.game.logic.action

import com.soywiz.korge.view.Container
import kotlin.test.Test
import kotlin.test.assertEquals


internal class ActionsManagerTest {

    @Test
    fun test() {
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
}
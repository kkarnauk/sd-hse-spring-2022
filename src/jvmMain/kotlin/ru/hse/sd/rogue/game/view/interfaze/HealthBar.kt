package ru.hse.sd.rogue.game.view.interfaze

import com.soywiz.kmem.toInt
import com.soywiz.kmem.toIntCeil
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.InterfaceState
import ru.hse.sd.rogue.game.view.Tiles
import ru.hse.sd.rogue.game.view.container.position

/**
 * Health bar
 */
class HealthBar(
    /**
     * [Container] to contain sprites of interface's parts.
     */
    container: Container,
    /**
     * Position of health bar
     */
    private val position: Position,
    /**
     * State of current interface
     */
    private val interfaceState: InterfaceState
) {
    private val fulls = List(5) { index ->
        container.sprite(Tiles.Interface.Heart.full).position(position(index)).also { it.visible = false }
    }

    private val halves = List(5) { index ->
        container.sprite(Tiles.Interface.Heart.half).position(position(index)).also { it.visible = false }
    }

    private val empties = List(5) { index ->
        container.sprite(Tiles.Interface.Heart.empty).position(position(index)).also { it.visible = false }
    }

    fun update() {
        val ratio = maxOf(0.0, interfaceState.health.current.toDouble() / interfaceState.health.maximum.toDouble())
        val filledHalves = (10.0 * ratio).toIntCeil()
        val halfFilled = filledHalves % 2 == 1
        val completelyFilled = (filledHalves - halfFilled.toInt()) / 2

        (0 until completelyFilled).forEach { fulls[it].visible = true }
        if (halfFilled) { halves[completelyFilled].visible = true }
        (completelyFilled + halfFilled.toInt() until 5).forEach { empties[it].visible = true }

        (completelyFilled until 5).forEach { fulls[it].visible = false }
        (0 until 5).forEach { if (!halfFilled || it != completelyFilled) halves[it].visible = false}
        (0 until completelyFilled + halfFilled.toInt()).forEach { empties[it].visible = false }
    }

    private fun position(index: Int): Position {
        return position + Position(index, 0)
    }
}

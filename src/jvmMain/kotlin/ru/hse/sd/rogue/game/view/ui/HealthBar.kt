package ru.hse.sd.rogue.game.view.ui

import com.soywiz.kmem.divCeil
import com.soywiz.kmem.toInt
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.view.Tiles
import ru.hse.sd.rogue.game.view.container.position

/**
 * Health bar
 */
class HealthBar(
    /**
     * [Container] to contain sprites of interface's parts.
     */
    private val container: Container,
    /**
     * Position of health bar
     */
    private val position: Position,
    /**
     * Player hearts
     */
    private val health: Health
) {
    private val fulls = mutableListOf<Sprite>()
    private val halves = mutableListOf<Sprite>()
    private val empties = mutableListOf<Sprite>()

    private fun fullSprite(index: Int): Sprite =
        container.sprite(Tiles.Interface.Heart.full).position(position(index)).also { it.visible = false }

    private fun halfSprite(index: Int): Sprite =
        container.sprite(Tiles.Interface.Heart.half).position(position(index)).also { it.visible = false }

    private fun emptySprite(index: Int): Sprite =
        container.sprite(Tiles.Interface.Heart.empty).position(position(index)).also { it.visible = false }

    private fun resizeMaxHealth(newMaxHealth: Int) {
        val newHearts = newMaxHealth.divCeil(2)
        if (newHearts < fulls.size) {
            throw UnsupportedOperationException()
        }
        (fulls.size until newHearts).forEach {
            fulls.add(fullSprite(it))
            halves.add(halfSprite(it))
            empties.add(emptySprite(it))
        }
    }

    fun update() {
        resizeMaxHealth(health.maximum)
        val halfFilled = health.current % 2 == 1
        val completelyFilled = health.current.div(2)

        (0 until completelyFilled).forEach { fulls[it].visible = true }
        if (halfFilled) {
            halves[completelyFilled].visible = true
        }
        (completelyFilled + halfFilled.toInt() until fulls.size).forEach { empties[it].visible = true }

        (completelyFilled until fulls.size).forEach { fulls[it].visible = false }
        (0 until fulls.size).forEach { if (!halfFilled || it != completelyFilled) halves[it].visible = false }
        (0 until completelyFilled + halfFilled.toInt()).forEach { empties[it].visible = false }
    }

    private fun position(index: Int): Position {
        return position + Position(index, 0)
    }
}

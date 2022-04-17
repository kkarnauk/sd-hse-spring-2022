package ru.hse.sd.rogue.game.view.ui

import com.soywiz.korge.ui.uiText
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.position.KorgePosition
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.view.Tiles
import ru.hse.sd.rogue.game.view.container.position

/**
 * Health bar
 */
class DamageBar(
    /**
     * [Container] to contain sprites of interface's parts.
     */
    container: Container,
    /**
     * Position of damage bar
     */
    position: Position,
    /**
     * PLayer damage
     */
    private val damage: Damage
) {

    init {
        container.sprite(Tiles.Interface.Damage.icon).position(position).also {
            it.scale = 0.5
        }
    }

    private val textValue = container.uiText("")
        .position((position + Position(1, 0)).asKorge() + KorgePosition(3, 0))

    fun update() {
        textValue.text = damage.average.toString()
    }
}

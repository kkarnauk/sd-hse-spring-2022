package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.logic.characteristics.Speed
import ru.hse.sd.rogue.game.state.State

/**
 * State of movement for a character.
 */
class MovementState(
    /**
     * [Speed] for a character.
     */
    val speed: Speed
) : State {
    /**
     * Whether a character can move.
     */
    var canMove: Boolean = true
}

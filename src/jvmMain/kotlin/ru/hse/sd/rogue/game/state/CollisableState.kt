package ru.hse.sd.rogue.game.state

import ru.hse.sd.rogue.game.logic.position.Position

/**
 * Represents a state that can be used to collide with other objects with [CollisableState].
 */
interface CollisableState : State {
    /**
     * Current position of an object.
     */
    val position: Position
}

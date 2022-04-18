package ru.hse.sd.rogue.game.controller

import ru.hse.sd.rogue.game.state.CollisableState

/**
 * Represents a controller that can handle collisions.
 */
interface CollisableController : Controller {
    /**
     * State of this controller.
     */
    val state: CollisableState

    /**
     * Handle collision with [other].
     */
    fun collideWith(other: CollisableController)

    /**
     * Handle no collision on this time.
     */
    fun noCollisions()
}

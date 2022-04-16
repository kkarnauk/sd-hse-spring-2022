package ru.hse.sd.rogue.game.controller

import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.action.registerRepeatable
import ru.hse.sd.rogue.game.logic.position.Position

/**
 * Handles all the collision in the game.
 *
 * On each tick:
 * * Collects all positions of [CollisableController].
 * * If a controller is the only one on a position, then [CollisableController.noCollisions] is called.
 * * If there are several controllers on the same position, then [CollisableController.collideWith]
 * is called on each pair of them (except pairs with the same controllers)
 */
class CollisionsController(
    /**
     * Actions manager to be used to register handles of collisions.
     */
    actionsManager: ActionsManager
) : Controller, IrreversibleAction {
    init {
        actionsManager.registerRepeatable(this)
    }

    private val collisableControllers = mutableListOf<CollisableController>()

    /**
     * Registers new [controller] to be being handled.
     */
    fun register(controller: CollisableController) {
        collisableControllers += controller
    }

    override fun invoke() {
        val positionToControllers = mutableMapOf<Position, MutableList<CollisableController>>()
        for (controller in collisableControllers) {
            positionToControllers
                .getOrPut(controller.state.position) { mutableListOf() }
                .add(controller)
        }

        for ((_, controllers) in positionToControllers) {
            if (controllers.size != 1) {
                for ((i, first) in controllers.withIndex()) {
                    for ((j, second) in controllers.withIndex()) {
                        if (i != j) {
                            first.collideWith(second)
                            second.collideWith(first)
                        }
                    }
                }
            } else {
                controllers.first().noCollisions()
            }
        }
    }
}

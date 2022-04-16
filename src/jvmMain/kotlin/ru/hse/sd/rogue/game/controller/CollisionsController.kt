package ru.hse.sd.rogue.game.controller

import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.action.registerRepeatable
import ru.hse.sd.rogue.game.logic.position.Position

class CollisionsController(
    actionsManager: ActionsManager
) : Controller, IrreversibleAction {
    init {
        actionsManager.registerRepeatable(this)
    }

    private val collisableControllers = mutableListOf<CollisableController>()

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
            if (controllers.size == 1) {
                controllers.first().noCollisions()
            } else {
                for (first in controllers) {
                    for (second in controllers) {
                        first.collideWith(second)
                        second.collideWith(first)
                    }
                }
            }
        }
    }
}

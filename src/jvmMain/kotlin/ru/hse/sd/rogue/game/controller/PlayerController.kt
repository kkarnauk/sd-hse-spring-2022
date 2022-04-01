package ru.hse.sd.rogue.game.controller

import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.position.Direction
import ru.hse.sd.rogue.game.state.character.Player

class PlayerController(
    actionsManager: ActionsManager,
    override val state: Player
) : CharacterController(actionsManager, state) {
    override fun move(direction: Direction) {
        TODO()
    }

    fun openInventory() {
        TODO()
    }
}

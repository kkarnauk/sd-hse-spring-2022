package ru.hse.sd.rogue.game.controller.character

import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.position.Direction
import ru.hse.sd.rogue.game.state.character.Player

class PlayerController(
    actionsManager: ActionsManager,
    override val state: Player
) : CharacterController(actionsManager, state) {
    override fun move(direction: Direction) {
        when (direction) {
            Direction.Up -> state.position.y--
            Direction.Down -> state.position.y++
            Direction.Left -> state.position.x--
            Direction.Right -> state.position.x++
        }
    }

    fun openInventory() {
        TODO()
    }
}

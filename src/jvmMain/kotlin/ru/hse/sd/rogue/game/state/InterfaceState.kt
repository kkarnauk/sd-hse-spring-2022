package ru.hse.sd.rogue.game.state

import ru.hse.sd.rogue.game.logic.characteristics.Health

/**
 * Represents a state of UI.
 */
class InterfaceState(
    val health: Health,
    val inventoryState: InventoryState
) : State

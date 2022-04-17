package ru.hse.sd.rogue.game.state

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Experience
import ru.hse.sd.rogue.game.logic.characteristics.Health

/**
 * Represents a state of UI.
 */
class InterfaceState(
    val health: Health,
    val inventoryState: InventoryState,
    val experience: Experience,
    val damage: Damage
) : State

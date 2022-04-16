package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.state.InventoryState

/**
 * State of the player.
 */
class PlayerState(
    health: Health,
    position: MutablePosition,
    meleeDamage: Damage,
    val inventoryState: InventoryState
) : CharacterState(health, position, meleeDamage)

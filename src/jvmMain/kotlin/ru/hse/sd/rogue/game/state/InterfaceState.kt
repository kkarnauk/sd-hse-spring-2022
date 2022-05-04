package ru.hse.sd.rogue.game.state

import ru.hse.sd.rogue.game.logic.characteristics.*

/**
 * Represents a state of UI.
 */
open class InterfaceState(
    /**
     * Player [Health].
     */
    open val health: Health,
    /**
     * Player [InventoryState].
     */
    open val inventoryState: InventoryState,
    /**
     * Player [Experience].
     */
    open val experience: Experience,
    /**
     * Player [Damage].
     */
    open val damage: Damage
) : State

/**
 * Mutable [InterfaceState].
 */
class InterfaceMutableState(
    override val health: MutableHealth,
    override val inventoryState: InventoryMutableState,
    override val experience: MutableExperience,
    override val damage: MutableDamage
) : InterfaceState(health, inventoryState, experience, damage), MutableState

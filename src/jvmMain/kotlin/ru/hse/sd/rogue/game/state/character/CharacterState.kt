package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.item.Weapon
import ru.hse.sd.rogue.game.logic.position.LookDirection
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.state.State

/**
 * General state of a character.
 */
abstract class CharacterState(
    /**
     * Current [Health] for this character.
     */
    val health: Health,
    /**
     * Current position for this character.
     */
    val position: MutablePosition,
    /**
     * [Damage] of this character without any weapon.
     */
    protected var meleeDamage: Damage,
    /**
     * Current [LookDirection] of this character.
     */
    var lookDirection: LookDirection = LookDirection.Right
) : State {
    /**
     * Whether this character is alive or not.
     */
    val isAlive: Boolean
        get() = health.current > 0

    /**
     * Current [Weapon] of this character.
     */
    var currentWeapon: Weapon? = null

    /**
     * Current [Damage] of this character.
     */
    val damage: Damage
        get() = currentWeapon?.damage ?: meleeDamage
}

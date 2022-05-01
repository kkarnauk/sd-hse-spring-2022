package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.common.Effect
import ru.hse.sd.rogue.game.logic.common.Id
import ru.hse.sd.rogue.game.logic.position.LookDirection
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.state.CollisableState

/**
 * General state of a character.
 */
sealed class CharacterState(
    /**
     * Current [Health] for this character.
     */
    val health: Health,
    /**
     * Current position for this character.
     */
    override val position: MutablePosition,
    /**
     * [Damage] of this character without any weapon.
     */
    protected var meleeDamage: Damage,
    /**
     * Current [LookDirection] of this character.
     */
    var lookDirection: LookDirection = LookDirection.Right
) : CollisableState {
    private val id = Id.create()

    /**
     * Whether this character is alive or not.
     */
    val isAlive: Boolean
        get() = health.current > 0

    /**
     * Current [Damage] of this character.
     */
    abstract val damage: Damage

    /**
     * Effect that this character can give while damaging.
     */
    abstract val effects: List<Effect>

    override fun equals(other: Any?): Boolean {
        return this === other || other is CharacterState && other.id == id
    }

    override fun hashCode(): Int = id.hashCode()
}

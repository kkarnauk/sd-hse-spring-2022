package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.characteristics.MutableDamage
import ru.hse.sd.rogue.game.logic.characteristics.MutableHealth
import ru.hse.sd.rogue.game.logic.common.Effect
import ru.hse.sd.rogue.game.logic.common.Id
import ru.hse.sd.rogue.game.logic.position.LookDirection
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.state.CollisableState
import ru.hse.sd.rogue.game.state.MutableState

/**
 * General state of a character.
 */
sealed class CharacterState : CollisableState {
    /**
     * Current [Health] for this character.
     */
    abstract val health: Health

    /**
     * [Damage] of this character without any weapon.
     */
    protected abstract val meleeDamage: Damage

    /**
     * Current [LookDirection] of this character.
     */
    abstract val lookDirection: LookDirection

    /**
     * Current [Damage] of this character.
     */
    abstract val damage: Damage

    /**
     * Effect that this character can give while damaging.
     */
    abstract val effects: List<Effect>


    private val id = Id.create()

    /**
     * Whether this character is alive or not.
     */
    val isAlive: Boolean
        get() = health.current > 0

    override fun equals(other: Any?): Boolean {
        return this === other || other is CharacterState && other.id == id
    }

    override fun hashCode(): Int = id.hashCode()
}

/**
 * Mutable [CharacterState].
 */
sealed class CharacterMutableState(
    override val health: MutableHealth,
    override val position: MutablePosition,
    override var meleeDamage: MutableDamage,
    override var lookDirection: LookDirection = LookDirection.Right
) : CharacterState(), MutableState {
    abstract override val damage: MutableDamage
}

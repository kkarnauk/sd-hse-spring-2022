package ru.hse.sd.rogue.game.logic.characteristics

/**
 * Experience of a character.
 */
abstract class Experience {
    /**
     * Current value of experience on [level].
     */
    abstract val xp: Int

    /**
     * Current level of experience.
     */
    abstract val level: Int
}

/**
 * Mutable [Experience].
 */
data class MutableExperience(
    override var xp: Int,
    override var level: Int
) : Experience()

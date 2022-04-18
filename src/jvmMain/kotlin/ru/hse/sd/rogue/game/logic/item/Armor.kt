package ru.hse.sd.rogue.game.logic.item

/**
 * Armor that can be put on a character.
 */
class Armor : Item() {
    // TODO
    override val usable: Boolean
        get() = TODO("Not yet implemented")

    /**
     * Different types of armor.
     */
    enum class Type {
        Iron,
        Golden
    }
}

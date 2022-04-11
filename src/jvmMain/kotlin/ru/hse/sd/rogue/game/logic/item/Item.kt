package ru.hse.sd.rogue.game.logic.item

import ru.hse.sd.rogue.game.controller.character.CharacterController

/**
 * A base item that can characters can storage in their inventories.
 */
sealed class Item {
    /**
     * Whether this item can be used now.
     */
    abstract val usable: Boolean

    /**
     * Use the item on [character].
     */
    open fun use(character: CharacterController) {
        require(usable) {
            "Cannot use non-usable item."
        }
    }

    // TODO
}

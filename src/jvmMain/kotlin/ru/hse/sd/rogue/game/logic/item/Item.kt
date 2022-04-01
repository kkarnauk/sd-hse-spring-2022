package ru.hse.sd.rogue.game.logic.item

import ru.hse.sd.rogue.game.controller.CharacterController

sealed class Item {
    abstract val usable: Boolean

    open fun use(character: CharacterController) {
        require(usable) {
            "Cannot use non-usable item."
        }
    }

    // TODO
}

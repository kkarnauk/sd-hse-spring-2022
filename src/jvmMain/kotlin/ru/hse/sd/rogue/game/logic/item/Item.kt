package ru.hse.sd.rogue.game.logic.item

import ru.hse.sd.rogue.game.state.character.CharacterState

sealed class Item {
    abstract val usable: Boolean

    open fun use(character: CharacterState) {
        require(usable) {
            "Cannot use non-usable item."
        }
    }

    // TODO
}

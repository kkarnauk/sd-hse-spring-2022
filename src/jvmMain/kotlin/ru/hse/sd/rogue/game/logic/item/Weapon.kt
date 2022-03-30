package ru.hse.sd.rogue.game.logic.item

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Durability
import ru.hse.sd.rogue.game.state.character.CharacterState

data class Weapon(
    val damage: Damage,
    val durability: Durability
) : Item() {
    override val usable: Boolean
        get() = durability.current > 0

    override fun use(character: CharacterState) {
        super.use(character)
        character.currentWeapon = this
    }
}

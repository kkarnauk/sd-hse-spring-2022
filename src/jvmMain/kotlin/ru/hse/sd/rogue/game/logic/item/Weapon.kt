package ru.hse.sd.rogue.game.logic.item

import ru.hse.sd.rogue.game.controller.character.CharacterController
import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Durability

data class Weapon(
    val damage: Damage,
    val durability: Durability
) : Item() {
    override val usable: Boolean
        get() = durability.current > 0

    override fun use(character: CharacterController) {
        super.use(character)
        character.update {
            takeWeapon(this@Weapon)
        }
    }
}

package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.controller.CharacterController
import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.position.Position

class Player(
    health: Health,
    position: Position,
    meleeDamage: Damage
) : CharacterState(health, position, meleeDamage) {
    // TODO
    override val controller: CharacterController
        get() = TODO("Not yet implemented")
}

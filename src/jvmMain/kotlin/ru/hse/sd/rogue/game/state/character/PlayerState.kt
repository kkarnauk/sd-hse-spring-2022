package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.position.Position

class PlayerState(
    health: Health,
    position: Position,
    meleeDamage: Damage
) : CharacterState(health, position, meleeDamage)

package ru.hse.sd.rogue.game.state.character.mob

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.character.CharacterState

open class Mob(
    health: Health,
    position: Position,
    meleeDamage: Damage
) : CharacterState(health, position, meleeDamage)

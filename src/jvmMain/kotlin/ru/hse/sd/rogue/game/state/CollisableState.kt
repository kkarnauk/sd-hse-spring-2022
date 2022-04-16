package ru.hse.sd.rogue.game.state

import ru.hse.sd.rogue.game.logic.position.Position

interface CollisableState : State {
    val position: Position
}

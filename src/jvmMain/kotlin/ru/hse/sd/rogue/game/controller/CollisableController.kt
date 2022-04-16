package ru.hse.sd.rogue.game.controller

import ru.hse.sd.rogue.game.state.CollisableState

abstract class CollisableController : Controller {
    abstract val state: CollisableState

    abstract fun collideWith(other: CollisableController)

    abstract fun noCollisions()
}

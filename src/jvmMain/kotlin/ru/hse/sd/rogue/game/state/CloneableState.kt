package ru.hse.sd.rogue.game.state

import ru.hse.sd.rogue.game.logic.ai.ReproductiveStrategy
import ru.hse.sd.rogue.game.state.character.ReproducingMoldMobState

/**
 * Interface for those states that can clone themselves. See example of usage in [ReproducingMoldMobState] and [ReproductiveStrategy]
 */
interface CloneableState<T : CloneableState<T>>: State {
    fun clone(): T
}
package ru.hse.sd.rogue.game.logic.ai

import ru.hse.sd.rogue.game.logic.action.Action
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.probably
import ru.hse.sd.rogue.game.logic.action.repeatEach

fun MobStrategy.repeat(frequency: Long,
                       actionsManager: ActionsManager,
                       action: Action): MobStrategy {
    actionsManager.register(action.repeatEach(frequency))
    return this
}

fun MobStrategy.randomlyRepeat(frequency: Long,
                               probability: Double,
                               actionsManager: ActionsManager,
                               action: Action): MobStrategy {
    actionsManager.register(action.probably(probability).repeatEach(frequency))
    return this
}
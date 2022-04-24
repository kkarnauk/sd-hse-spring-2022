package ru.hse.sd.rogue.game.logic.ai

import ru.hse.sd.rogue.game.logic.action.*

fun MobStrategy.repeat(frequency: Long,
                       actionsManager: ActionsManager,
                       action: IrreversibleAction): MobStrategy {
    actionsManager.register(action.withFrequency(frequency))
    return this
}

fun MobStrategy.randomlyRepeat(frequency: Long,
                               probability: Double,
                               actionsManager: ActionsManager,
                               action: IrreversibleAction): MobStrategy {
    actionsManager.registerRepeatable(action.probably(probability).withFrequency(frequency))
    return this
}
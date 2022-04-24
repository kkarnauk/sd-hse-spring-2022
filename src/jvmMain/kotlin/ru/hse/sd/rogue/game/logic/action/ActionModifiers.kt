package ru.hse.sd.rogue.game.logic.action

import kotlin.random.Random

/**
 * Execute this action only each [frequency] ticks
 */
fun IrreversibleAction.withFrequency(frequency: Long): IrreversibleAction {
    require(frequency > 0)
    return object : IrreversibleAction {
        var currentTimer: Long = 0

        override fun invoke() {
            currentTimer++
            if (currentTimer >= frequency) {
                currentTimer = 0
                this@withFrequency.invoke()
            }
        }
    }
}

/**
 * Execute this action with probability [probability]
 */
fun IrreversibleAction.probably(probability: Double): IrreversibleAction {
    require(probability in 0.0..1.0)
    return IrreversibleAction { if (Random.nextFloat() < probability) this@probably.invoke() }
}
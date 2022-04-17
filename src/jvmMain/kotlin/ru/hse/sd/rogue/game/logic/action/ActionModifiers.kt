package ru.hse.sd.rogue.game.logic.action

import kotlin.random.Random

fun Action.repeatEach(frequency: Long): Action {
    assert(frequency > 0)
    return object : IrreversibleAction {
        var currentTimer: Long = 0

        override fun invoke() {
            currentTimer++
            if (currentTimer >= frequency) {
                currentTimer = 0
                this@repeatEach.invoke()
            }
        }
    }
}

fun Action.probably(probability: Double): Action {
    assert(probability in 0.0..1.0)
    return IrreversibleAction { if (Random.nextFloat() < probability) this@probably.invoke() }
}
package ru.hse.sd.rogue.game.logic.action

import java.util.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.schedule
import kotlin.concurrent.withLock

class ActionsManager(
    private val invokePeriod: Long
) {
    init {
        Timer(true).schedule(delay = invokePeriod, period = invokePeriod) { invokeActions() }
    }

    private var tick: Long = 0L
    private val lock = ReentrantLock()

    private val registeredActions: MutableList<Action> = mutableListOf()
    private val reversedActions: MutableList<Pair<Action, Long>> = mutableListOf()

    private fun invokeActions() {
        tick += invokePeriod

        val actions = lock.withLock {
            val actions = registeredActions.toMutableList()
            registeredActions.clear()
            actions
        }

        val leftReversedActions = mutableListOf<Pair<Action, Long>>()
        for ((reversedAction, startTime) in reversedActions) {
            if (startTime <= tick) {
                actions += reversedAction
            } else {
                leftReversedActions += reversedAction to startTime
            }
        }

        reversedActions.clear()
        reversedActions += leftReversedActions

        for (action in actions) {
            action.invoke()
            if (action is ReversibleAction) {
                reversedActions += action.reverse() to action.lifetime.time + tick
            }
        }
    }

    fun register(action: Action) {
        lock.withLock {
            registeredActions += action
        }
    }

    fun unregister(action: Action) {
        lock.withLock {
            require(registeredActions.remove(action)) {
                "Cannot unregister an action: it doesn't exist."
            }
        }
    }
}

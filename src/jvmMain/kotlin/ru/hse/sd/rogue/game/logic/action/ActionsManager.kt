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

    private val registeredActions: MutableList<PrioritisedAction> = mutableListOf()
    private val reversedActions: MutableList<Pair<PrioritisedAction, Long>> = mutableListOf()

    private fun invokeActions() {
        tick += invokePeriod

        val actions = lock.withLock {
            val actions = registeredActions.toMutableList()
            registeredActions.clear()
            actions
        }

        val leftReversedActions = mutableListOf<Pair<PrioritisedAction, Long>>()
        for ((reversedAction, startTime) in reversedActions) {
            if (startTime <= tick) {
                actions += reversedAction
            } else {
                leftReversedActions += reversedAction to startTime
            }
        }

        reversedActions.clear()
        reversedActions += leftReversedActions

        val (highActions, normalActions) = actions.partition { (_, priority) -> priority == ActionPriority.High }

        for ((action, priority) in (highActions + normalActions)) {
            action.invoke()
            if (action is ReversibleAction) {
                reversedActions += PrioritisedAction(action.reverse(), priority) to action.lifetime.time + tick
            }
        }
    }

    fun register(priority: ActionPriority, action: Action) {
        lock.withLock {
            registeredActions += PrioritisedAction(action, priority)
        }
    }

    fun register(action: Action) {
        register(ActionPriority.Normal, action)
    }

    fun unregister(action: Action) {
        lock.withLock {
            require(registeredActions.removeIf { (currentAction, _) -> currentAction == action }) {
                "Cannot unregister an action: it doesn't exist."
            }
        }
    }
}

private data class PrioritisedAction(
    val action: Action,
    val priority: ActionPriority
)

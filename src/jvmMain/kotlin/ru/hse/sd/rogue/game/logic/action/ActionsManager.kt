package ru.hse.sd.rogue.game.logic.action

import com.soywiz.klock.timesPerSecond
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.addFixedUpdater
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class ActionsManager(
    private val container: Container,
    private val timesPerSecond: Int
) {

    private var started = false

    private var tick: Long = 0L
    private val lock = ReentrantLock()

    private val registeredActions: MutableList<PrioritisedAction> = mutableListOf()
    private val reversedActions: MutableList<Pair<PrioritisedAction, Long>> = mutableListOf()

    private fun invokeActions() {
        tick++

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

        for ((action, priority) in actions.sortedByDescending { it.priority }) {
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

    fun start() {
        if (started) {
            throw IllegalStateException()
        }
        started = true
        container.addFixedUpdater(timesPerSecond.timesPerSecond) {
            invokeActions()
        }
    }
}

fun ActionsManager.registerRepeatable(action: IrreversibleAction) {
    registerRepeatable(ActionPriority.Normal, action)
}

fun ActionsManager.registerRepeatable(priority: ActionPriority, action: IrreversibleAction) {
    fun register() {
        register(priority, IrreversibleAction {
            action.invoke()
            register()
        })
    }
    register()
}

private data class PrioritisedAction(
    val action: Action,
    val priority: ActionPriority
)

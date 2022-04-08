package ru.hse.sd.rogue.game.logic.action

import com.soywiz.klock.timesPerSecond
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.addFixedUpdater
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * Manages and invoke actions on every 'tick'.
 *
 * On each 'tick' invokes all collected actions with respect to their [ActionPriority].
 */
class ActionsManager(
    /**
     * [Container] to be used for registering timer.
     */
    private val container: Container,
    /**
     * Number of 'ticks' per second.
     */
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
                reversedActions += PrioritisedAction(action.reverse(), priority) to action.lifetime.ticks + tick
            }
        }
    }

    /**
     * Registers new [action] with its [priority].
     */
    fun register(priority: ActionPriority, action: Action) {
        lock.withLock {
            registeredActions += PrioritisedAction(action, priority)
        }
    }

    /**
     * Registers new [action] with [ActionPriority.Normal] priority.
     */
    fun register(action: Action) {
        register(ActionPriority.Normal, action)
    }

    /**
     * Starts managing and invoking of actions.
     */
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

/**
 * Registers [action] with [ActionPriority.Normal] priority that invokes on **each** tick.
 */
fun ActionsManager.registerRepeatable(action: IrreversibleAction) {
    registerRepeatable(ActionPriority.Normal, action)
}

/**
 * Registers [action] with [priority] that invokes on **each** tick.
 */
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

package ru.hse.sd.rogue.game.logic.action

/**
 * Action that has 'back' effect. After [lifetime] a manager invokes [reverse].
 */
interface ReversibleAction : Action {
    /**
     * Number of ticks before [reverse].
     */
    val lifetime: Lifetime

    /**
     * 'Back' effect of this action.
     */
    fun reverse(): Action
}

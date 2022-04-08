package ru.hse.sd.rogue.game.logic.action

/**
 * An action that can be sent to [ActionsManager] and be invoked on the 'tick'.
 */
sealed interface Action {
    /**
     * Directly the action to be invoked.
     */
    fun invoke()
}

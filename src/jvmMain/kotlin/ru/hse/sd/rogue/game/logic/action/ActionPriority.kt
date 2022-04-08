package ru.hse.sd.rogue.game.logic.action

/**
 * Priority of actions in [ActionsManager].
 * The higher priority, the earlier it is invoked within one 'tick'.
 *
 * There is no fixed order for actions with the same priorities.
 */
enum class ActionPriority {
    High,
    Normal,
    Low
}

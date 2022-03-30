package ru.hse.sd.rogue.game.logic.action

interface ReversibleAction : Action {
    val lifetime: Lifetime

    fun reverse(): Action
}

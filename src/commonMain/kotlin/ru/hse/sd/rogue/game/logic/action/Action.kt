package ru.hse.sd.rogue.game.logic.action

interface Action {
    // TODO params
    fun invoke(): Result

    class Result {
        // TODO
    }
}

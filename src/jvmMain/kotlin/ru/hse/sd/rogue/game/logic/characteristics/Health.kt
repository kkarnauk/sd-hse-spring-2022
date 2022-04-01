package ru.hse.sd.rogue.game.logic.characteristics

data class Health(
    var maximum: Int,
    var current: Int = maximum
)

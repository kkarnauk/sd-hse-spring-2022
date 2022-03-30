package ru.hse.sd.rogue.game.logic.characteristics

data class Damage(
    val minimum: Int,
    val maximum: Int
) {
    val value: Int
        get() = (minimum..maximum).random()
}

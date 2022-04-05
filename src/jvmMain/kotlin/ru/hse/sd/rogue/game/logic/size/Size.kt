package ru.hse.sd.rogue.game.logic.size

data class Size(
    val width: Int,
    val height: Int
) {
    fun asKorge(): KorgeSize = KorgeSize(width * cellSize, height * cellSize)
}

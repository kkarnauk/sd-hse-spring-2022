package ru.hse.sd.rogue.game.logic.size

/**
 * Size to be used directly for the game.
 */
data class Size(
    /**
     * Width.
     */
    val width: Int,
    /**
     * Height.
     */
    val height: Int
) {
    /**
     * Convert [Size] into [KorgeSize].
     */
    fun asKorge(): KorgeSize = KorgeSize(width * cellSize, height * cellSize)
}

package ru.hse.sd.rogue.game.logic.level

import ru.hse.sd.rogue.game.state.CellState
import ru.hse.sd.rogue.game.state.character.CharacterState

/**
 * Game level
 */
data class Level(
    /**
     * Level cells
     */
    val cells: List<List<CellState>>,
    /**
     * State of level characters
     */
    val characters: List<CharacterState>
)
package ru.hse.sd.rogue.game.logic.level

import ru.hse.sd.rogue.game.state.CellState
import ru.hse.sd.rogue.game.state.character.CharacterState


data class Level(
    val cells: List<List<CellState>>,
    val characters: List<CharacterState>
) {
    fun getBuilder() = LevelBuilder()
}
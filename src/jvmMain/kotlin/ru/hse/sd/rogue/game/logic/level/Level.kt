package ru.hse.sd.rogue.game.logic.level

import ru.hse.sd.rogue.game.state.CellState
import ru.hse.sd.rogue.game.state.character.CharacterState

/**
 * Game level
 */
class Level(
    /**
     * Level cells
     */
    val cells: List<List<CellState>>,
    /**
     * State of level characters
     */
    characters: List<CharacterState>
) {
    private val charactersBacking = characters.toMutableList()
    val characters: List<CharacterState>
        get() = charactersBacking

    fun addCharacter(characterState: CharacterState) {
        charactersBacking.add(characterState)
    }
}
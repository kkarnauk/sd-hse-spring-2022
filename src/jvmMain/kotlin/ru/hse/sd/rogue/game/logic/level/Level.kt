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
    private val myCharacters = characters.toMutableList()

    val characters: List<CharacterState>
        get() = myCharacters

    fun addCharacter(characterState: CharacterState) {
        myCharacters.add(characterState)
    }
}
package ru.hse.sd.rogue.game.logic.level

import ru.hse.sd.rogue.game.state.CellState
import ru.hse.sd.rogue.game.state.character.CharacterMutableState

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
    characters: List<CharacterMutableState>
) {
    private val myCharacters = characters.toMutableList()

    val characters: List<CharacterMutableState>
        get() = myCharacters

    fun addCharacter(characterState: CharacterMutableState) {
        myCharacters.add(characterState)
    }
}
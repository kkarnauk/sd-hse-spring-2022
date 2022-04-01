package ru.hse.sd.rogue.game.controller

import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.registerRepeatable
import ru.hse.sd.rogue.game.state.MapState
import ru.hse.sd.rogue.game.state.character.Player
import ru.hse.sd.rogue.game.view.CharacterView
import ru.hse.sd.rogue.game.view.LevelView

class MapController(
    actionsManager: ActionsManager,
    mapState: MapState,
    player: Player
) : Controller {
    private val levelView = LevelView(actionsManager.mapContainer, mapState)
    private val characterView = CharacterView(actionsManager.characterContainer, player)

    init {
        actionsManager.registerRepeatable(levelView)
        actionsManager.registerRepeatable(characterView)
    }
}

package ru.hse.sd.rogue.game.controller

import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.state.MapState
import ru.hse.sd.rogue.game.view.CharacterView
import ru.hse.sd.rogue.game.view.LevelView

class MapController(
    actionsManager: ActionsManager,
    mapState: MapState,
) : Controller {
    private val levelView = LevelView(actionsManager.mapContainer, mapState)
    private val characterView = CharacterView(actionsManager.characterContainer, mapState)

    init {
        actionsManager.register(
            levelView
        )
        actionsManager.register(
            characterView
        )
    }
}

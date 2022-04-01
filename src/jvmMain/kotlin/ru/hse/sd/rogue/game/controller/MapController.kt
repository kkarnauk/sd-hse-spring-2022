package ru.hse.sd.rogue.game.controller

import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.state.MapState

class MapController(
    actionsManager: ActionsManager,
    private val state: MapState
) : Controller

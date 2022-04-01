package ru.hse.sd.rogue

import com.soywiz.korge.Korge
import ru.hse.sd.rogue.game.controller.GlobalController
import ru.hse.sd.rogue.game.controller.MapController
import ru.hse.sd.rogue.game.controller.character.PlayerController
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.cell.CellContent
import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.input.InputHandler
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.CellState
import ru.hse.sd.rogue.game.state.MapState
import ru.hse.sd.rogue.game.state.character.PlayerState
import ru.hse.sd.rogue.game.view.MapView
import ru.hse.sd.rogue.game.view.character.PlayerView
import kotlin.math.abs


// TODO remove after implementing map generator and loader in hw3
private fun generateSimpleMap(): List<List<CellState>> {
    val size = 20
    return List(size) { x ->
        List(size) { y ->
            val content = when {
                minOf(x, y) == 0 || maxOf(x, y) + 1 == size -> CellContent.Wall
                abs(x - size / 3) <= 1 && abs(y - size / 3) <= 1 -> CellContent.Wall
                else -> CellContent.Space
            }
            CellState(Position(x, y), content)
        }
    }
}

suspend fun main() = Korge(width = 600, height = 600, virtualWidth = 512, virtualHeight = 512) {
    val actionsManager = ActionsManager(this, 30)

    val playerState = PlayerState(
        Health(100),
        Position(10, 10),
        Damage(100, 100)
    )

    val mapState = MapState(generateSimpleMap())

    val playerController = PlayerController(
        actionsManager,
        playerState
    )
    val mapController = MapController(
        actionsManager,
        mapState
    )
    val globalController = GlobalController()

    val inputHandler = InputHandler(playerController).apply {
        mapKeys()
    }

    MapView(actionsManager, actionsManager.mapContainer, mapState)
    PlayerView(actionsManager, actionsManager.characterContainer, playerState)

    actionsManager.start()
}
package ru.hse.sd.rogue

import com.soywiz.korge.Korge
import ru.hse.sd.rogue.game.controller.GlobalController
import ru.hse.sd.rogue.game.controller.MapController
import ru.hse.sd.rogue.game.controller.PlayerController
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.cell.CellContent
import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.CellState
import ru.hse.sd.rogue.game.state.MapState
import ru.hse.sd.rogue.game.state.character.Player
import kotlin.math.abs


// TODO remove after implementing map generator and loader in hw3
private fun generateSimpleMap(): List<List<CellState>> {
    val size = 20
    return List(size) { x ->
        List(size) { y ->
            val content = when {
                minOf(x, y) == 0 || maxOf(x, y) + 1 == size -> CellContent.Wall
                x == size / 2 && y == size / 2 -> CellContent.Player
                abs(x - size / 3) <= 1 && abs(y - size / 3) <= 1 -> CellContent.Wall
                else -> CellContent.Space
            }
            CellState(Position(x, y), content)
        }
    }
}

suspend fun main() = Korge(width = 600, height = 600, virtualWidth = 512, virtualHeight = 512) {
    val actionsManager = ActionsManager(this, 30)

    val playerController = PlayerController(
        actionsManager,
        Player(
            Health(100),
            Position(10, 10),
            Damage(100, 100)
        )
    )
    val mapController = MapController(
        actionsManager,
        MapState(generateSimpleMap())
    )
    val globalController = GlobalController()

    actionsManager.start()
}
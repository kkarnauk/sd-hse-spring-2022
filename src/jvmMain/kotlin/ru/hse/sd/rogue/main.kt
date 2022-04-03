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
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.CellState
import ru.hse.sd.rogue.game.state.MapState
import ru.hse.sd.rogue.game.state.character.PlayerState
import ru.hse.sd.rogue.game.state.character.mob.boss.BigDemonMobState
import ru.hse.sd.rogue.game.state.character.mob.regular.*
import ru.hse.sd.rogue.game.view.MapView
import ru.hse.sd.rogue.game.view.character.mob.boss.BigDemonView
import ru.hse.sd.rogue.game.view.character.mob.regular.*
import ru.hse.sd.rogue.game.view.character.player.PlayerView
import kotlin.math.abs


// TODO remove after implementing map generator and loader in hw3
private fun generateSimpleMap(): List<List<CellState>> {
    val size = 50
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

suspend fun main() = Korge(width = 600, height = 600, virtualWidth = 480, virtualHeight = 480) {
    val actionsManager = ActionsManager(this, 30)

    val playerState = PlayerState(
        Health(100),
        MutablePosition(10, 10),
        Damage(100, 100)
    )

    val mapState = MapState(generateSimpleMap())

    val mapController = MapController(
        actionsManager,
        mapState
    )
    val playerController = PlayerController(
        actionsManager,
        playerState,
        mapController
    )
    val globalController = GlobalController()

    val inputHandler = InputHandler(playerController).apply {
        mapKeys()
    }

    val bossState = BigDemonMobState(MutablePosition(2, 2))

    MapView(actionsManager, actionsManager.mapContainer, mapState)
    PlayerView(actionsManager, actionsManager.characterContainer, playerState)
    BigDemonView(actionsManager, actionsManager.characterContainer, bossState)

    GoblinView(actionsManager, actionsManager.characterContainer, GoblinMobState(MutablePosition(4, 4)))
    ImpView(actionsManager, actionsManager.characterContainer, ImpMobState(MutablePosition(6, 6)))
    NecromanterView(actionsManager, actionsManager.characterContainer, NecromanterMobState(MutablePosition(8, 8)))
    SkeletView(actionsManager, actionsManager.characterContainer, SkeletMobState(MutablePosition(10, 10)))
    TinyZombieView(actionsManager, actionsManager.characterContainer, TinyZombieMobState(MutablePosition(12, 12)))

    actionsManager.start()
}
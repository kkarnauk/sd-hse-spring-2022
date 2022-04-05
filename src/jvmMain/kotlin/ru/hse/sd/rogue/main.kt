package ru.hse.sd.rogue

import com.soywiz.korge.Korge
import com.soywiz.korge.view.Stage
import ru.hse.sd.rogue.game.controller.GlobalController
import ru.hse.sd.rogue.game.controller.MapController
import ru.hse.sd.rogue.game.controller.character.PlayerController
import ru.hse.sd.rogue.game.logic.action.ActionPriority
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.registerRepeatable
import ru.hse.sd.rogue.game.logic.cell.CellContent
import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.input.InputHandler
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.logic.size.KorgeSize
import ru.hse.sd.rogue.game.logic.size.Size
import ru.hse.sd.rogue.game.state.CellState
import ru.hse.sd.rogue.game.state.MapState
import ru.hse.sd.rogue.game.state.character.PlayerState
import ru.hse.sd.rogue.game.state.character.mob.boss.BigDemonMobState
import ru.hse.sd.rogue.game.state.character.mob.regular.*
import ru.hse.sd.rogue.game.view.CameraView
import ru.hse.sd.rogue.game.view.MapView
import ru.hse.sd.rogue.game.view.character.mob.boss.BigDemonView
import ru.hse.sd.rogue.game.view.character.mob.regular.*
import ru.hse.sd.rogue.game.view.character.player.PlayerView
import ru.hse.sd.rogue.game.view.container.ContainersManager
import kotlin.math.abs

val cameraSize = Size(30, 30)
val windowSize = Size(50, 50)

val cameraKorgeSize = cameraSize.asKorge()
val windowKorgeSize = windowSize.asKorge()

// TODO remove after implementing map generator and loader in hw3
private fun generateSimpleMap(): List<List<CellState>> {
    val size = 50
    return List(size) { x ->
        List(size) { y ->
            val content = when {
                minOf(x, y) == 0 || maxOf(x, y) + 1 == size -> CellContent.Wall
                x % 8 == 0 && y % 13 == 0 -> CellContent.Wall
                abs(x - size / 3) <= 1 && abs(y - size / 3) <= 1 -> CellContent.Wall
                else -> CellContent.Space
            }
            CellState(Position(x, y), content)
        }
    }
}

suspend operator fun Korge.invoke(windowSize: KorgeSize, cameraSize: KorgeSize, entry: Stage.() -> Unit) {
    return invoke(
        width = windowSize.width,
        height = windowSize.height,
        virtualWidth = cameraSize.width,
        virtualHeight = cameraSize.height,
        entry = entry
    )
}

suspend fun main() = Korge(windowKorgeSize, cameraKorgeSize) {
    val containersManager = ContainersManager(this)
    val actionsManager = ActionsManager(containersManager.camera, 30)

    val playerState = PlayerState(
        Health(100),
        MutablePosition(10, 10),
        Damage(100, 100)
    )

    val cameraView = CameraView(windowSize, cameraSize, containersManager.camera) {
        playerState.position
    }

    actionsManager.registerRepeatable(ActionPriority.Low, cameraView)

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

    MapView(actionsManager, containersManager.mapContainer, mapState)
    PlayerView(actionsManager, containersManager.characterContainer, playerState)
    BigDemonView(actionsManager, containersManager.characterContainer, bossState)

    GoblinView(actionsManager, containersManager.characterContainer, GoblinMobState(MutablePosition(4, 4)))
    ImpView(actionsManager, containersManager.characterContainer, ImpMobState(MutablePosition(20, 20)))
    NecromanterView(actionsManager, containersManager.characterContainer, NecromanterMobState(MutablePosition(8, 8)))
    SkeletView(actionsManager, containersManager.characterContainer, SkeletMobState(MutablePosition(33, 10)))
    TinyZombieView(actionsManager, containersManager.characterContainer, TinyZombieMobState(MutablePosition(12, 12)))

    actionsManager.start()
}
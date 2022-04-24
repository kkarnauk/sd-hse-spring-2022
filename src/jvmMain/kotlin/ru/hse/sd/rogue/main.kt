package ru.hse.sd.rogue

import com.soywiz.korge.Korge
import com.soywiz.korge.view.Stage
import ru.hse.sd.rogue.game.controller.CollisionsController
import ru.hse.sd.rogue.game.controller.GlobalController
import ru.hse.sd.rogue.game.controller.MapController
import ru.hse.sd.rogue.game.controller.MobViewFactory
import ru.hse.sd.rogue.game.controller.character.MobController
import ru.hse.sd.rogue.game.controller.character.MovementController
import ru.hse.sd.rogue.game.controller.character.PlayerController
import ru.hse.sd.rogue.game.controller.item.LootItemController
import ru.hse.sd.rogue.game.logic.action.ActionPriority
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.registerRepeatable
import ru.hse.sd.rogue.game.logic.ai.*
import ru.hse.sd.rogue.game.logic.ai.withExpirableEffects
import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Durability
import ru.hse.sd.rogue.game.logic.characteristics.Speed
import ru.hse.sd.rogue.game.logic.common.Confusion
import ru.hse.sd.rogue.game.logic.input.InputHandler
import ru.hse.sd.rogue.game.logic.item.Potion
import ru.hse.sd.rogue.game.logic.item.Weapon
import ru.hse.sd.rogue.game.logic.level.level
import ru.hse.sd.rogue.game.logic.level.mobsfabric.ClassicDungeonMobsFactory
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.logic.size.KorgeSize
import ru.hse.sd.rogue.game.logic.size.Size
import ru.hse.sd.rogue.game.state.InterfaceState
import ru.hse.sd.rogue.game.state.MapState
import ru.hse.sd.rogue.game.state.character.MovementState
import ru.hse.sd.rogue.game.state.character.MobState
import ru.hse.sd.rogue.game.state.character.PlayerState
import ru.hse.sd.rogue.game.state.character.ReproductingMoldMobState
import ru.hse.sd.rogue.game.state.item.weapon.LootPotionState
import ru.hse.sd.rogue.game.state.item.weapon.LootWeaponState
import ru.hse.sd.rogue.game.view.CameraView
import ru.hse.sd.rogue.game.view.MapView
import ru.hse.sd.rogue.game.view.ViewRegistrationManager
import ru.hse.sd.rogue.game.view.container.ContainersManager
import ru.hse.sd.rogue.game.view.item.potion.LootBluePotionView
import ru.hse.sd.rogue.game.view.item.weapon.LootAxView
import ru.hse.sd.rogue.game.view.item.weapon.LootSwordView
import ru.hse.sd.rogue.game.view.ui.InterfaceView
import java.time.Instant
import java.time.temporal.ChronoUnit

/**
 * Size of camera used for the game.
 */
val cameraSize = Size(30, 30)

/**
 * Size of the game window.
 */
val mapSize = Size(50, 50)

/**
 * Size of camera used for KorGE API.
 */
val cameraKorgeSize = cameraSize.asKorge()

/**
 * Size of the game window for KorGE API.
 */
val mapWindowSize = mapSize.asKorge()

suspend operator fun Korge.invoke(mapSize: KorgeSize, cameraSize: KorgeSize, entry: Stage.() -> Unit) {
    return invoke(
        width = mapSize.width,
        height = mapSize.height,
        virtualWidth = cameraSize.width,
        virtualHeight = cameraSize.height,
        entry = entry
    )
}

suspend fun main() = Korge(mapWindowSize, cameraKorgeSize) {
    val containersManager = ContainersManager(this)
    val actionsManager = ActionsManager(containersManager.camera, 30)

    @Suppress("UNUSED_VARIABLE") val globalController = GlobalController()

    val gameLevel = level {
        generate {
            map.width = mapSize.width
            map.height = mapSize.height
            map.border = 1
            map.corridorThickness = 0 to 1
            map.corridorWobbling = 0.05
            map.minRoomSize = 7
            map.splitNumIterations = 4
            mobs.mobsFactory = listOf(ClassicDungeonMobsFactory()/*, AnimalMobsFactory()*/).random()
        }
    }


    val mapState = MapState(gameLevel.cells)
    val mapController = MapController(
        mapState
    )

    val createMovementController = { ticksToReload: Int ->
        MovementController(actionsManager, MovementState(Speed(ticksToReload)), mapController)
    }

    MapView(containersManager.mapContainer, mapState).also { it.register(actionsManager) }

    val collisionsController = CollisionsController().also { it.register(actionsManager) }

    val playerState = gameLevel.characters.filterIsInstance<PlayerState>().single()

    val playerController = PlayerController(
        actionsManager,
        playerState,
        createMovementController(1)
    ).apply { collisionsController.register(this) }

//    PlayerView(containersManager.characterContainer, playerState).also { it.register(actionsManager) }
    InputHandler(playerController).apply {
        mapKeys()
    }

    val cameraView = CameraView(mapSize, cameraSize, containersManager.camera) {
        playerState.position
    }
    actionsManager.registerRepeatable(ActionPriority.Low, cameraView)

    val mobViewFactory = MobViewFactory(containersManager.characterContainer)

    ViewRegistrationManager(gameLevel, actionsManager, mobViewFactory, containersManager).also {
        it.register(actionsManager)
    }

//    run {
//        with(mobViewFactory) {
//                gameLevel.characters.forEach { state ->
//                    state.toView().also { view -> view.register(actionsManager) }
//                }
//            }
//        }
//    }

    gameLevel.characters.filterIsInstance<MobState>().filter { it !is ReproductingMoldMobState }.forEach { state ->
        MobController(
            actionsManager, state, createMovementController(5), AggressiveStrategy(
                playerState, state, createMovementController(3), 5
            ).withExpirableEffects()
        )
            .also { it.register() }
            .apply { collisionsController.register(this) }
    }
    gameLevel.characters.filterIsInstance<ReproductingMoldMobState>().forEach { state ->
        val movementController = createMovementController(7)
        MobController(
            actionsManager,
            state,
            movementController,
            ReproductiveStrategy(
                state,
                100,
                0.5,
                gameLevel,
                movementController,
                actionsManager,
                collisionsController
            ).withExpirableEffects()
        )
            .also { it.register() }
            .apply { collisionsController.register(this) }
    }

    run {
        InterfaceView(
            containersManager.interfaceContainer,
            InterfaceState(
                playerState.health,
                playerState.inventoryState,
                playerState.experience,
                playerState.damage
            ),
            cameraSize
        ).also {
            it.register(actionsManager)
        }
    }

    run {
        val ax = Weapon(Damage(10, 20), Durability(100), Weapon.Type.Ax)
        val sword = Weapon(Damage(10, 20), Durability(100), Weapon.Type.Sword)
        val swordState = LootWeaponState(sword, MutablePosition(10, 10))
        val axState = LootWeaponState(ax, MutablePosition(13, 13))

        LootItemController(swordState).apply { collisionsController.register(this) }
        LootItemController(axState).apply { collisionsController.register(this) }

        LootSwordView(containersManager.lootItemsContainer, swordState).also { it.register(actionsManager) }.invoke()
        LootAxView(containersManager.lootItemsContainer, axState).also { it.register(actionsManager) }.invoke()

        val confusingPotion = Potion(Confusion(Instant.now().plus(1L, ChronoUnit.MINUTES)))
        val potionState = LootPotionState(confusingPotion, MutablePosition(14, 17))

        LootItemController(potionState).apply { collisionsController.register(this) }

        LootBluePotionView(containersManager.lootItemsContainer, potionState).also {
            it.register(actionsManager)
        }.invoke()
    }

    actionsManager.start()
}

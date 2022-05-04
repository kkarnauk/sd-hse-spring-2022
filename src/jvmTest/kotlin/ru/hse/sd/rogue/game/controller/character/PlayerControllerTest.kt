package ru.hse.sd.rogue.game.controller.character

import com.soywiz.korge.view.Container
import ru.hse.sd.rogue.game.controller.MapController
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.cell.CellContent
import ru.hse.sd.rogue.game.logic.characteristics.MutableDamage
import ru.hse.sd.rogue.game.logic.characteristics.MutableHealth
import ru.hse.sd.rogue.game.logic.characteristics.MutableSpeed
import ru.hse.sd.rogue.game.logic.position.Direction
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.CellState
import ru.hse.sd.rogue.game.state.InventoryMutableState
import ru.hse.sd.rogue.game.state.MapState
import ru.hse.sd.rogue.game.state.character.MovementMutableState
import ru.hse.sd.rogue.game.state.character.PlayerState
import kotlin.test.Test
import kotlin.test.assertEquals

internal class PlayerControllerTest {

    @Test
    fun move() {
        val actionsManager = ActionsManager(Container(), 30)
        actionsManager.start()
        val playerState = PlayerState(
            MutableHealth(10),
            MutablePosition(0, 0),
            MutableDamage(0, 1),
            InventoryMutableState()
        )
        val playerController = PlayerController(
            actionsManager,
            playerState,
            MovementController(
                actionsManager,
                MovementMutableState(MutableSpeed(1)),
                MapController(
                    MapState(
                        listOf(
                            listOf(
                                CellState(Position(0, 0), CellContent.Space),
                                CellState(Position(0, 1), CellContent.Space)
                            ),
                            listOf(
                                CellState(Position(1, 0), CellContent.Space),
                                CellState(Position(1, 1), CellContent.Space)
                            )
                        )
                    )
                )
            )
        )
        assertEquals(Position(0, 0), playerState.position)
        playerController.move(Direction.Right)
        actionsManager.manualInvoke()

        assertEquals(Position(1, 0), playerState.position)
        playerController.move(Direction.Down)
        actionsManager.manualInvoke()

        assertEquals(Position(1, 0), playerState.position)
        playerController.move(Direction.Down)
        actionsManager.manualInvoke()

        assertEquals(Position(1, 1), playerState.position)
        playerController.move(Direction.Left)
        actionsManager.manualInvoke()

        assertEquals(Position(1, 1), playerState.position)
        playerController.move(Direction.Left)
        actionsManager.manualInvoke()

        assertEquals(Position(0, 1), playerState.position)
        playerController.move(Direction.Up)
        actionsManager.manualInvoke()

        assertEquals(Position(0, 1), playerState.position)
        playerController.move(Direction.Up)
        actionsManager.manualInvoke()

        assertEquals(Position(0, 0), playerState.position)
        playerController.move(Direction.Up)
        actionsManager.manualInvoke()

        playerController.move(Direction.Up)
        actionsManager.manualInvoke()
        assertEquals(Position(0, 0), playerState.position)
    }
}
package ru.hse.sd.rogue.game.controller.character

import com.soywiz.korge.view.Container
import org.junit.Ignore
import org.junit.Test
import ru.hse.sd.rogue.game.controller.MapController
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.cell.CellContent
import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.position.Direction
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.CellState
import ru.hse.sd.rogue.game.state.MapState
import ru.hse.sd.rogue.game.state.character.PlayerState
import kotlin.test.assertEquals
import kotlin.test.assertFails

internal class PlayerControllerTest {

    @Test
    fun move() {
        val actionsManager = ActionsManager(Container(), 30)
        actionsManager.start()
        val playerState = PlayerState(Health(10), MutablePosition(0, 0), Damage(0, 1))
        val playerController = PlayerController(
            actionsManager,
            playerState,
            MovementController(
                MapController(
                    actionsManager, MapState(
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
        assertEquals(Position(1, 1), playerState.position)
        playerController.move(Direction.Left)
        actionsManager.manualInvoke()
        assertEquals(Position(0, 1), playerState.position)
        playerController.move(Direction.Up)
        actionsManager.manualInvoke()
        assertEquals(Position(0, 0), playerState.position)

        playerController.move(Direction.Up)
        assertFails {
            actionsManager.manualInvoke()
        }
    }

    @Test
    @Ignore
    fun openInventory() {
        TODO()
    }
}
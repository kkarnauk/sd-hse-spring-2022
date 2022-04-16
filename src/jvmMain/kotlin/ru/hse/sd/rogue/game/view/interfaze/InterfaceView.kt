package ru.hse.sd.rogue.game.view.interfaze

import com.soywiz.korge.view.Container
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.action.registerRepeatable
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.logic.size.Size
import ru.hse.sd.rogue.game.state.InterfaceState
import ru.hse.sd.rogue.game.view.View
import ru.hse.sd.rogue.game.view.interfaze.inventory.InventoryView

/**
 * View of the interface.
 */
class InterfaceView(
    /**
     * General actions' manager to update this view.
     */
    actionsManager: ActionsManager,
    /**
     * [Container] to contain sprites of interface's parts.
     */
    container: Container,
    /**
     * State of current interface
     */
    interfaceState: InterfaceState,
    /**
     * Size of camera
     */
    cameraSize: Size
) : View, IrreversibleAction {
    private val height: Int = cameraSize.height - 2

    private val healthBar = HealthBar(
        container,
        Position(2, height),
        interfaceState
    )

    private val inventoryView = InventoryView(
        container,
        interfaceState.inventoryState,
        Position(8, height)
    )

    init {
        actionsManager.registerRepeatable(this)
    }

    override fun invoke() {
        healthBar.update()
        inventoryView.invoke()
    }
}

package ru.hse.sd.rogue.game.view.ui

import com.soywiz.korge.view.Container
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.action.registerRepeatable
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.logic.size.Size
import ru.hse.sd.rogue.game.state.InterfaceState
import ru.hse.sd.rogue.game.view.View
import ru.hse.sd.rogue.game.view.ui.inventory.InventoryView

/**
 * View of the interface.
 */
class InterfaceView(
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
    private val healthBar = HealthBar(
        container,
        Position(0, 0),
        interfaceState.health
    )

    private val damageBar = DamageBar(
        container,
        Position(0, 1),
        interfaceState.damage
    )

    private val experienceBar = ExperienceBar(
        container,
        Position(0, 2),
        interfaceState.experience
    )

    private val inventoryView = InventoryView(
        container,
        interfaceState.inventoryState,
        Position(cameraSize.width - 4, 0)
    )


    override fun register(actionsManager: ActionsManager) {
        actionsManager.registerRepeatable(this)
    }

    override fun invoke() {
        healthBar.update()
        damageBar.update()
        experienceBar.update()
        inventoryView.invoke()
    }
}

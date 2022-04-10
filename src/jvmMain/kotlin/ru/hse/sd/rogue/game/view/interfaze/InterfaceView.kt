package ru.hse.sd.rogue.game.view.interfaze

import com.soywiz.korge.view.Container
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.action.registerRepeatable
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.logic.size.Size
import ru.hse.sd.rogue.game.state.InterfaceState
import ru.hse.sd.rogue.game.view.View

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
    private val healthBar = HealthBar(
        container,
        Position(2, cameraSize.height - 2),
        interfaceState
    )

    init {
        actionsManager.registerRepeatable(this)
    }

    override fun invoke() {
        healthBar.invoke()
    }
}

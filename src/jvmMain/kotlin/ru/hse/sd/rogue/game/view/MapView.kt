package ru.hse.sd.rogue.game.view

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.action.registerRepeatable
import ru.hse.sd.rogue.game.logic.cell.CellContent
import ru.hse.sd.rogue.game.state.MapState
import ru.hse.sd.rogue.game.view.container.position

/**
 * View of a map.
 */
class MapView(
    /**
     * General actions' manager to update this view.
     */
    actionsManager: ActionsManager,
    /**
     * [Container] to contain sprites of cells for this map.
     */
    container: Container,
    /**
     * State of this map.
     */
    mapState: MapState
) : View, IrreversibleAction {
    init {
        mapState.forEach { mapCell ->
            when (mapCell.content) {
                CellContent.Wall -> {
                    container.sprite(Tiles.Map.wall)
                        .position(mapCell.position)
                        .playAnimation()
                }
                else -> {
                    container.sprite(Tiles.Map.space)
                        .position(mapCell.position)
                        .playAnimation()
                }
            }
        }

        actionsManager.registerRepeatable(this)
    }

    override fun invoke() = Unit
}

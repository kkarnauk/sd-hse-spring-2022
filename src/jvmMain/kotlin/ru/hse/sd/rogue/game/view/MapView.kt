package ru.hse.sd.rogue.game.view

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.position
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.action.registerRepeatable
import ru.hse.sd.rogue.game.logic.cell.CellContent
import ru.hse.sd.rogue.game.state.MapState


class MapView(
    actionsManager: ActionsManager,
    container: Container,
    mapState: MapState
) : View, IrreversibleAction {
    init {
        mapState.forEach { mapCell ->
            when (mapCell.content) {
                CellContent.Wall -> {
                    container.sprite(Tiles.Map.wall)
                        .position(mapCell.position.x * cellSize, mapCell.position.y * cellSize)
                        .playAnimation()
                }
                else -> {
                    container.sprite(Tiles.Map.space)
                        .position(mapCell.position.x * cellSize, mapCell.position.y * cellSize)
                        .playAnimation()
                }
            }
        }

        actionsManager.registerRepeatable(this)
    }

    override fun invoke() {
    }
}

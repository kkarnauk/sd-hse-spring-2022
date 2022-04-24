package ru.hse.sd.rogue.game.view

import ru.hse.sd.rogue.game.controller.MobViewFactory
import ru.hse.sd.rogue.game.logic.action.ActionPriority
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.action.registerRepeatable
import ru.hse.sd.rogue.game.logic.level.Level
import ru.hse.sd.rogue.game.state.character.CharacterState
import ru.hse.sd.rogue.game.state.character.MobState
import ru.hse.sd.rogue.game.state.character.PlayerState
import ru.hse.sd.rogue.game.view.character.player.PlayerView
import ru.hse.sd.rogue.game.view.container.ContainersManager

/**
 * Automatically register views of new mobs on the map
 */
class ViewRegistrationManager(
    private val gameLevel: Level,
    private val actionsManager: ActionsManager,
    private val mobViewFactory: MobViewFactory,
    private val containersManager: ContainersManager
): IrreversibleAction {
    private val registeredState = HashMap<CharacterState, View>()

    override fun invoke() {
        gameLevel.characters.filterNot { registeredState.contains(it) }.forEach { state ->
            val view = when (state) {
                is PlayerState -> PlayerView(containersManager.characterContainer, state)
                is MobState -> with(mobViewFactory) { state.toView() }
            }

            registeredState[state] = view
            view.register(actionsManager)
        }
    }

    override fun register(actionsManager: ActionsManager) {
        actionsManager.registerRepeatable(ActionPriority.Low,this)
    }
}
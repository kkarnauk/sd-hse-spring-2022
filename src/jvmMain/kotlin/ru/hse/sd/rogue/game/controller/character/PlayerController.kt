package ru.hse.sd.rogue.game.controller.character

import com.soywiz.kmem.toIntCeil
import ru.hse.sd.rogue.game.controller.CollisableController
import ru.hse.sd.rogue.game.controller.item.LootItemController
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.position.takeAway
import ru.hse.sd.rogue.game.state.InventoryState
import ru.hse.sd.rogue.game.state.character.PlayerState
import ru.hse.sd.rogue.game.state.item.LootItemState
import kotlin.math.log2
import kotlin.random.Random

/**
 * Responsible for controlling the player.
 */
class PlayerController(
    actionsManager: ActionsManager,
    override val state: PlayerState,
    movementController: MovementController
) : CharacterController(actionsManager, state, movementController) {
    private var lootCandidate: LootItemState? = null

    /**
     * `Loot candidate` is an item that will be put into the inventory if a player down the corresponding key.
     *
     * This method put the candidate (if it exists) into the inventory.
     */
    fun putLootCandidateInInventory() {
        lootCandidate?.let {
            if (state.inventoryState.addItem(it.item)) {
                it.position.takeAway()
            }
        }
        lootCandidate = null
    }

    override fun noCollisions() {
        super.noCollisions()
        lootCandidate = null
    }

    override fun collideWith(other: CollisableController) {
        lootCandidate = (other as? LootItemController)?.state

        if (other is CharacterController) {
            other.takeDamage(state.damage)
            if (!other.state.isAlive) {
                addExperience(other.xpForKilling)

            }
        }
    }

    private fun addExperience(xp: Int) {
        state.experience.xp += xp
        val newLevel = log2(1 + state.experience.xp.div(10).toDouble()).toIntCeil()
        repeat(newLevel - state.experience.level) {
            levelUp()
        }
    }

    private fun levelUp() {
        state.experience.level++
        if (Random.nextInt() % 2 == 0) {
            state.health.maximum += 2
            state.health.current += 2
        } else {
            state.damage.minimum += 1
            state.damage.maximum += 1
        }
    }

    /**
     * Invokes [action] on [InventoryState] of the player.
     */
    fun updateInventory(action: InventoryState.() -> Unit) {
        actionsManager.register(IrreversibleAction { action(state.inventoryState) })
    }
}

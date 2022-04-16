package ru.hse.sd.rogue.game.view.interfaze.inventory

import com.soywiz.korge.view.*
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.item.Armor
import ru.hse.sd.rogue.game.logic.item.Item
import ru.hse.sd.rogue.game.logic.item.Potion
import ru.hse.sd.rogue.game.logic.item.Weapon
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.InventoryState
import ru.hse.sd.rogue.game.view.Tiles
import ru.hse.sd.rogue.game.view.View
import ru.hse.sd.rogue.game.view.container.position
import ru.hse.sd.rogue.game.view.interfaze.inventory.item.InventoryItemView
import ru.hse.sd.rogue.game.view.interfaze.inventory.item.weapon.InventoryAxView
import ru.hse.sd.rogue.game.view.interfaze.inventory.item.weapon.InventorySwordView

class InventoryView(
    private val container: Container,
    private val inventoryState: InventoryState,
    startPosition: Position
) : View, IrreversibleAction {
    private val weaponPositions =
        startPosition..Position(startPosition.x + inventoryState.maxSize - 1, startPosition.y)

    private val armorPositions = weaponPositions.last().let { last ->
        Position(last.x + 2, last.y)..Position(last.x + inventoryState.maxSize + 1, last.y)
    }

    private val potionPositions = armorPositions.last().let { last ->
        Position(last.x + 2, last.y)..Position(last.x + inventoryState.maxSize + 1, last.y)
    }

    private fun drawPlaces(spriteAnimation: SpriteAnimation, visible: Boolean) = buildList {
        val positions = listOf(weaponPositions, armorPositions, potionPositions).flatten()
        for (pos in positions) {
            add(container.sprite(spriteAnimation).position(pos).visible(visible))
        }
    }

    private val availablePlaceSprites: List<Sprite> = drawPlaces(Tiles.Interface.ItemPlace.available, true)

    private val chosenPlaceSprites: List<Sprite> = drawPlaces(Tiles.Interface.ItemPlace.chosen, false)

    private val currentChosenPositions: MutableList<Int> = mutableListOf()

    fun updatePlaces() {
        currentChosenPositions.forEach { chosenPlaceSprites[it].visible = false }

        val chosenPositions = listOfNotNull(
            inventoryState.currentWeapon,
            inventoryState.currentArmor?.let { it + inventoryState.maxSize },
            inventoryState.currentPotion?.let { it + 2 * inventoryState.maxSize }
        )

        chosenPositions.forEach { chosenPlaceSprites[it].visible = true }

        currentChosenPositions.clear()
        currentChosenPositions.addAll(chosenPositions)
    }

    private fun drawItems(items: List<Item>, positions: List<Position>) {
        items.zip(positions).forEach { (item, position) ->
            item.toView(position).invoke()
        }
    }

    private fun updateItems() {
        drawItems(inventoryState.weapons, weaponPositions)
        drawItems(inventoryState.armors, armorPositions)
        drawItems(inventoryState.potions, potionPositions)
    }

    override fun invoke() {
        updatePlaces()
        updateItems()
    }

    private fun Item.toView(position: Position): InventoryItemView {
        return when (this) {
            is Weapon -> when (type) {
                Weapon.Type.Ax -> InventoryAxView(container, this, position)
                Weapon.Type.Sword -> InventorySwordView(container, this, position)
            }
            is Armor -> TODO()
            is Potion -> TODO()
        }
    }
}

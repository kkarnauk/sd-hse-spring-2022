package ru.hse.sd.rogue.game.state

import ru.hse.sd.rogue.game.logic.item.Armor
import ru.hse.sd.rogue.game.logic.item.Item
import ru.hse.sd.rogue.game.logic.item.Potion
import ru.hse.sd.rogue.game.logic.item.Weapon

/**
 * State of an inventory of a character.
 */
class InventoryState : State {
    private val myWeapons = mutableListOf<Weapon>()
    private val myArmors = mutableListOf<Armor>()
    private val myPotions = mutableListOf<Potion>()

    val weapons: List<Weapon> get() = myWeapons
    val armors: List<Armor> get() = myArmors
    val potions: List<Potion> get() = myPotions

    var currentWeaponIndex: Int? = null
    var currentArmorIndex: Int? = null
    var currentPotionIndex: Int? = null

    val currentWeapon: Weapon?
        get() = currentWeaponIndex?.let { weapons.getOrNull(it) }

    val currentArmor: Armor?
        get() = currentArmorIndex?.let { armors.getOrNull(it) }

    val currentPotion: Potion?
        get() = currentPotionIndex?.let { potions.getOrNull(it) }

    val maxSize: Int
        get() = 3

    private fun <T : Item> MutableList<T>.addItem(element: T): Boolean {
        if (size >= maxSize) return false
        this += element
        return true
    }

    fun addItem(item: Item): Boolean {
        return when (item) {
            is Weapon -> myWeapons.addItem(item)
            is Armor -> myArmors.addItem(item)
            is Potion -> myPotions.addItem(item)
        }
    }
}

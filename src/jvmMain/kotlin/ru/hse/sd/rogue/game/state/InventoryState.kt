package ru.hse.sd.rogue.game.state

import ru.hse.sd.rogue.game.logic.item.Armor
import ru.hse.sd.rogue.game.logic.item.Item
import ru.hse.sd.rogue.game.logic.item.Potion
import ru.hse.sd.rogue.game.logic.item.Weapon
import kotlin.properties.Delegates

/**
 * State of an inventory of the player.
 */
class InventoryState : VersionableState() {
    private val myWeapons = mutableListOf<Weapon>()
    private val myArmors = mutableListOf<Armor>()
    private val myPotions = mutableListOf<Potion>()

    /**
     * List of [Weapon] for the player. Size cannot exceed [maxSize].
     */
    val weapons: List<Weapon> get() = myWeapons

    /**
     * List of [Armor] for the player. Size cannot exceed [maxSize].
     */
    val armors: List<Armor> get() = myArmors

    /**
     * List of [Potion] for the player. Size cannot exceed [maxSize].
     */
    val potions: List<Potion> get() = myPotions

    /**
     * Index of the currently chosen weapon.
     */
    var currentWeaponIndex: Int? by Delegates.vetoable(null) { _, oldValue, newValue ->
        if (oldValue != newValue) updateVersion()
        newValue == null || newValue in 0 until maxSize
    }

    /**
     * Index of the currently chosen armor.
     */
    var currentArmorIndex: Int? by Delegates.vetoable(null) { _, oldValue, newValue ->
        if (oldValue != newValue) updateVersion()
        newValue == null || newValue in 0 until maxSize
    }

    /**
     * Index of the currently chosen potion.
     */
    var currentPotionIndex: Int? by Delegates.vetoable(null) { _, oldValue, newValue ->
        if (oldValue != newValue) updateVersion()
        newValue == null || newValue in 0 until maxSize
    }

    /**
     * Currently chosen weapon.
     */
    val currentWeapon: Weapon?
        get() = currentWeaponIndex?.let { weapons.getOrNull(it) }

    /**
     * Currently chosen armor.
     */
    val currentArmor: Armor?
        get() = currentArmorIndex?.let { armors.getOrNull(it) }

    /**
     * Currently chosen potion.
     */
    val currentPotion: Potion?
        get() = currentPotionIndex?.let { potions.getOrNull(it) }

    /**
     * Maximal size of the items of each type that can be in the inventory at the same type.
     */
    val maxSize: Int
        get() = 3

    private fun <T : Item> MutableList<T>.addItem(element: T): Boolean {
        if (size >= maxSize) {
            return false
        }

        this += element
        updateVersion()
        return true
    }

    /**
     * Tries to put a new [item] in the inventory
     * @return whether it's successful (e.g. maybe no space)
     */
    fun addItem(item: Item): Boolean {
        return when (item) {
            is Weapon -> myWeapons.addItem(item)
            is Armor -> myArmors.addItem(item)
            is Potion -> myPotions.addItem(item)
            else -> TODO("investigate compilation error")
        }
    }

    private fun <T : Item> MutableList<T>.dropItem(index: Int?) {
        if (index != null && size > index) {
            removeAt(index)
            updateVersion()
        }
    }

    /**
     * Tries to drop [currentWeapon].
     */
    fun dropWeapon() = myWeapons.dropItem(currentWeaponIndex)

    /**
     * Tries to drop [currentArmor].
     */
    fun dropArmor() = myWeapons.dropItem(currentArmorIndex)

    /**
     * Tries to drop [currentPotion].
     */
    fun dropPotion() = myWeapons.dropItem(currentPotionIndex)
}

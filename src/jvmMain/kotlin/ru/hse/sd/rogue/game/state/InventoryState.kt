package ru.hse.sd.rogue.game.state

import ru.hse.sd.rogue.game.logic.item.Armor
import ru.hse.sd.rogue.game.logic.item.Item
import ru.hse.sd.rogue.game.logic.item.Potion
import ru.hse.sd.rogue.game.logic.item.Weapon

/**
 * State of an inventory of the player.
 */
abstract class InventoryState : VersionableState() {
    protected val myWeapons = mutableListOf<Weapon>()
    protected val myArmors = mutableListOf<Armor>()
    protected val myPotions = mutableListOf<Potion>()

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
    abstract val currentWeaponIndex: Int?

    /**
     * Index of the currently chosen armor.
     */
    abstract val currentArmorIndex: Int?

    /**
     * Index of the currently chosen potion.
     */
    abstract val currentPotionIndex: Int?

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
}

class InventoryMutableState : InventoryState(), MutableState {
    override var currentWeaponIndex: Int? = null
        set(value) {
            if (field != value) updateVersion()
            field = value
        }

    override var currentArmorIndex: Int? = null
        set(value) {
            if (field != value) updateVersion()
            field = value
        }

    override var currentPotionIndex: Int? = null
        set(value) {
            if (field != value) updateVersion()
            field = value
        }

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

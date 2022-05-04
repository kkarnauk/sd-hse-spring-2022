package ru.hse.sd.rogue.game.state

import junit.framework.TestCase.*
import org.junit.Test
import ru.hse.sd.rogue.game.logic.characteristics.MutableDamage
import ru.hse.sd.rogue.game.logic.characteristics.MutableDurability
import ru.hse.sd.rogue.game.logic.item.Weapon

class InventoryStateTest {
    private val sword = Weapon(MutableDamage(100, 100), MutableDurability(100), Weapon.Type.Sword)
    private val ax = Weapon(MutableDamage(100, 100), MutableDurability(100), Weapon.Type.Ax)

    @Test
    fun `test add item`() {
        val inventory = InventoryMutableState()
        assertTrue(inventory.weapons.isEmpty())
        assertTrue(inventory.armors.isEmpty())
        assertTrue(inventory.potions.isEmpty())

        inventory.addItem(sword)
        inventory.addItem(ax)
        assertEquals(listOf(sword, ax), inventory.weapons)
        assertTrue(inventory.armors.isEmpty())
        assertTrue(inventory.potions.isEmpty())

        inventory.addItem(sword)
        inventory.addItem(ax)

        assertEquals(listOf(sword, ax, sword), inventory.weapons)
    }

    @Test
    fun `test drop item`() {
        val inventory = InventoryMutableState()
        inventory.addItem(sword)
        inventory.addItem(ax)
        inventory.addItem(sword)
        inventory.currentWeaponIndex = 1
        inventory.dropWeapon()
        assertEquals(listOf(sword, sword), inventory.weapons)
    }

    @Test
    fun `test versions`() {
        val inventory = InventoryMutableState()
        val version1 = inventory.version
        inventory.addItem(sword)
        assertTrue(inventory.hasChangedSince(version1))

        val version2 = inventory.version
        assertFalse(inventory.hasChangedSince(version2))

        inventory.currentWeaponIndex = 2
        assertTrue(inventory.hasChangedSince(version2))
        val version3 = inventory.version

        inventory.currentWeaponIndex = 2
        assertFalse(inventory.hasChangedSince(version3))

        inventory.dropWeapon()
        assertFalse(inventory.hasChangedSince(version3))

        inventory.currentWeaponIndex = 0
        inventory.dropWeapon()
        assertTrue(inventory.hasChangedSince(version2))
    }
}

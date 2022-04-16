package ru.hse.sd.rogue.game.logic.input

import com.soywiz.korev.Key
import com.soywiz.korge.input.KeysEvents
import com.soywiz.korge.input.keys
import com.soywiz.korge.view.Stage
import ru.hse.sd.rogue.game.controller.character.PlayerController
import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.position.Direction

/**
 * Maps keys for controlling the player.
 */
class InputHandler(private val playerController: PlayerController) {
    /**
     * Maps keys for controlling the player.
     */
    fun Stage.mapKeys() {
        keys {
            mapMovementController()
            mapInventoryController()
            mapHelps()
        }
    }

    private fun KeysEvents.mapMovementController() {
        down(Key.W) {
            playerController.update {
                move(Direction.Up)
            }
        }
        down(Key.S) {
            playerController.update {
                move(Direction.Down)
            }
        }
        down(Key.A) {
            playerController.update {
                move(Direction.Left)
            }
        }
        down(Key.D) {
            playerController.update {
                move(Direction.Right)
            }
        }
    }

    private fun KeysEvents.mapInventoryController() {
        down(Key.SPACE) {
            playerController.update {
                playerController.putCandidateInInventory()
            }
        }

        down(Key.NUMPAD1) {
            playerController.updateInventory {
                currentWeaponIndex = 0
            }
        }
        down(Key.NUMPAD2) {
            playerController.updateInventory {
                currentWeaponIndex = 1
            }
        }
        down(Key.NUMPAD3) {
            playerController.updateInventory {
                currentWeaponIndex = 2
            }
        }

        down(Key.NUMPAD4) {
            playerController.updateInventory {
                currentArmorIndex = 0
            }
        }
        down(Key.NUMPAD5) {
            playerController.updateInventory {
                currentArmorIndex = 1
            }
        }
        down(Key.NUMPAD6) {
            playerController.updateInventory {
                currentArmorIndex = 2
            }
        }

        down(Key.NUMPAD7) {
            playerController.updateInventory {
                currentPotionIndex = 0
            }
        }
        down(Key.NUMPAD8) {
            playerController.updateInventory {
                currentPotionIndex = 1
            }
        }
        down(Key.NUMPAD9) {
            playerController.updateInventory {
                currentPotionIndex = 2
            }
        }
    }

    // TODO remove
    private fun KeysEvents.mapHelps() {
        down(Key.F) {
            playerController.takeDamage(Damage(10, 10))
        }
    }
}

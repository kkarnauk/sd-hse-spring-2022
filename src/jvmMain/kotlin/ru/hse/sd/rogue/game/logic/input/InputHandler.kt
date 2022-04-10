package ru.hse.sd.rogue.game.logic.input

import com.soywiz.korev.Key
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
            // TODO: remove it later
            down(Key.F) {
                playerController.takeDamage(Damage(10, 10))
            }
        }
    }
}

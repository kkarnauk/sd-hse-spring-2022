package ru.hse.sd.rogue.game.logic.input

import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.view.Stage
import ru.hse.sd.rogue.game.logic.position.Direction
import ru.hse.sd.rogue.game.state.character.Player

class InputHandler(private val player: Player) {
    fun Stage.mapKeys() {
        keys {
            down(Key.W) {
                player.update {
                    controller.move(Direction.Up)
                }
            }
            down(Key.S) {
                player.update {
                    controller.move(Direction.Down)
                }
            }
            down(Key.A) {
                player.update {
                    controller.move(Direction.Left)
                }
            }
            down(Key.D) {
                player.update {
                    controller.move(Direction.Right)
                }
            }
        }
    }
}

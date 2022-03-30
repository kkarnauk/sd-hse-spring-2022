package ru.hse.sd.rogue.game.logic.input

import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.view.Stage
import ru.hse.sd.rogue.game.controller.PlayerController
import ru.hse.sd.rogue.game.logic.position.Direction

class InputHandler(private val controller: PlayerController) {
    fun Stage.mapKeys() {
        keys {
            down(Key.W) {
                controller.move(Direction.Up)
            }
            down(Key.S) {
                controller.move(Direction.Down)
            }
            down(Key.A) {
                controller.move(Direction.Left)
            }
            down(Key.D) {
                controller.move(Direction.Right)
            }
        }
    }
}

package ru.hse.sd.rogue.game.view

import com.soywiz.korge.view.Camera
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.logic.size.Size
import ru.hse.sd.rogue.game.view.container.position

/**
 * Responsible to correctly move and adjust [camera].
 */
class CameraView(
    /**
     * [Size] of the whole map (i.e. game window).
     */
    private val mapSize: Size,
    /**
     * [Size] of this camera.
     */
    private val cameraSize: Size,
    /**
     * Container of the camera.
     */
    private val camera: Camera,
    /**
     * Position used to calculate positions of this camera.
     */
    private val positionAnchor: () -> Position
) : View, IrreversibleAction {
    private val topLeft: MutablePosition = MutablePosition(0, 0)

    private val bottomRight: Position
        get() = Position(topLeft.x + cameraSize.width - 1, topLeft.y + cameraSize.height - 1)

    private val minDeltaX: Int
        get() = cameraSize.width / 4

    private val minDeltaY: Int
        get() = cameraSize.height / 4

    init {
        topLeft.replaceWith(updateTopLeft())
        camera.position(-topLeft)
    }

    private fun MutablePosition.fixWithLimits(): MutablePosition = apply {
        x = maxOf(0, minOf(x, mapSize.width - cameraSize.width))
        y = maxOf(0, minOf(y, mapSize.height - cameraSize.height))
    }

    private fun updateTopLeft(): Position {
        val anchor = positionAnchor()
        val (leftDeltaX, topDeltaY) = anchor - topLeft
        val (rightDeltaX, bottomDeltaY) = bottomRight - anchor
        val newTopLeft = topLeft.asMutable()

        if (leftDeltaX < minDeltaX) {
            newTopLeft.x -= minDeltaX - leftDeltaX
        } else if (rightDeltaX < minDeltaX) {
            newTopLeft.x += minDeltaX - rightDeltaX
        }

        if (topDeltaY < minDeltaY) {
            newTopLeft.y -= minDeltaY - topDeltaY
        } else if (bottomDeltaY < minDeltaY) {
            newTopLeft.y += minDeltaY - bottomDeltaY
        }

        return newTopLeft.fixWithLimits()
    }

    override fun invoke() {
        val newTopLeft = updateTopLeft()
        if (newTopLeft != topLeft) {
            topLeft.replaceWith(newTopLeft)
            camera.position(-topLeft)
        }
    }
}

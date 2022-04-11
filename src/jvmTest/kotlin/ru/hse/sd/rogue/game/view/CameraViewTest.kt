package ru.hse.sd.rogue.game.view

import com.soywiz.korge.view.Camera
import com.soywiz.korma.geom.Point
import kotlin.test.assertEquals
import kotlin.test.Test
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.logic.size.Size


internal class CameraViewTest {

    @Test
    operator fun invoke() {
        val position = MutablePosition(0, 0)
        val camera = Camera()
        val cameraView = CameraView(Size(10, 10), Size(5, 5), camera) { position }
        assertEquals(Point(0, 0), camera.pos)
        position.replaceWith(MutablePosition(100, 100))
        cameraView.invoke()
        assertEquals(Point(-80, -80), camera.pos)
    }
}
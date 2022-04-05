package ru.hse.sd.rogue.game.view.container

import com.soywiz.korge.view.*

class ContainersManager(
    stage: Stage
) {
    val camera: Camera = stage.camera()

    val mapContainer: Container = camera.container()

    val characterContainer: Container = camera.container()
}

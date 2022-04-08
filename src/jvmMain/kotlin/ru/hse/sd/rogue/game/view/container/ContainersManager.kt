package ru.hse.sd.rogue.game.view.container

import com.soywiz.korge.view.*

/**
 * Manages all containers in the game.
 */
class ContainersManager(
    /**
     * [Stage] for the game.
     */
    stage: Stage
) {
    /**
     * [Camera] of the game.
     */
    val camera: Camera = stage.camera()

    /**
     * [Container] for drawing a map.
     */
    val mapContainer: Container = camera.container()

    /**
     * [Container] for drawing characters.
     */
    val characterContainer: Container = camera.container()
}

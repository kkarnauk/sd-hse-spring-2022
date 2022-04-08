package ru.hse.sd.rogue.game.view.container

import com.soywiz.korge.view.position
import com.soywiz.korge.view.View
import ru.hse.sd.rogue.game.logic.position.KorgePosition
import ru.hse.sd.rogue.game.logic.position.Position

/**
 * Util function to change positions of views using [KorgePosition].
 */
fun <T : View> T.position(pos: KorgePosition): T = position(pos.x, pos.y)

/**
 * Util function to change positions of views using [Position].
 */
fun <T : View> T.position(pos: Position): T = position(pos.asKorge())

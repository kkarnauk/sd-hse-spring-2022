package ru.hse.sd.rogue.game.view

import com.soywiz.korge.view.SpriteAnimation
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.runBlocking

object Tiles {
    private val dungeonTileset2 = runBlocking {
        resourcesVfs["0x72_DungeonTilesetII_v1.4.png"].readBitmap()
    }

    object Map {
        val space = SpriteAnimation(
            spriteMap = dungeonTileset2,
            spriteWidth = 16,
            spriteHeight = 16,
            marginTop = 64,
            marginLeft = 16
        )
        val wall = SpriteAnimation(
            spriteMap = dungeonTileset2,
            spriteWidth = 16,
            spriteHeight = 16,
            marginTop = 17,
            marginLeft = 20
        )
    }

    object Player {
        val idle = SpriteAnimation(
            spriteMap = dungeonTileset2,
            spriteWidth = 16,
            spriteHeight = 28,
            marginTop = 68,
            marginLeft = 128,
            columns = 4
        )
    }

    object Mobs {
        object BigDemon {
            val idle = SpriteAnimation(
                spriteMap = dungeonTileset2,
                spriteWidth = 32,
                spriteHeight = 36,
                marginTop = 364,
                marginLeft = 16,
                columns = 4
            )
        }

        object Imp {
            val idle = SpriteAnimation(
                spriteMap = dungeonTileset2,
                spriteWidth = 16,
                spriteHeight = 16,
                marginTop = 48,
                marginLeft = 365,
                columns = 4
            )
        }

        object Goblin {
            val idle = SpriteAnimation(
                spriteMap = dungeonTileset2,
                spriteWidth = 16,
                spriteHeight = 16,
                marginTop = 32,
                marginLeft = 368,
                columns = 4
            )
        }

        object TinyZombie {
            val idle = SpriteAnimation(
                spriteMap = dungeonTileset2,
                spriteWidth = 16,
                spriteHeight = 16,
                marginTop = 16,
                marginLeft = 368,
                columns = 4
            )
        }

        object Skelet {
            val idle = SpriteAnimation(
                spriteMap = dungeonTileset2,
                spriteWidth = 16,
                spriteHeight = 16,
                marginTop = 80,
                marginLeft = 368,
                columns = 4
            )
        }

        object Necromancer {
            val idle = SpriteAnimation(
                spriteMap = dungeonTileset2,
                spriteWidth = 16,
                spriteHeight = 20,
                marginTop = 268,
                marginLeft = 368,
                columns = 4
            )
        }
    }

}
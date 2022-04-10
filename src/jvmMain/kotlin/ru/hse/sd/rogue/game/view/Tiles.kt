package ru.hse.sd.rogue.game.view

import com.soywiz.korge.view.SpriteAnimation
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.runBlocking

/**
 * Contains tiles for different parts of the game.
 */
object Tiles {
    private val dungeonTileset2 = runBlocking {
        resourcesVfs["0x72_DungeonTilesetII_v1.4.png"].readBitmap()
    }

    /**
     * Tiles for a map.
     */
    object Map {
        /**
         * Tile for an empty cell.
         */
        val space = SpriteAnimation(
            spriteMap = dungeonTileset2,
            spriteWidth = 16,
            spriteHeight = 16,
            marginTop = 64,
            marginLeft = 16
        )

        /**
         * Tile for a wall-cell.
         */
        val wall = SpriteAnimation(
            spriteMap = dungeonTileset2,
            spriteWidth = 16,
            spriteHeight = 16,
            marginTop = 17,
            marginLeft = 20
        )
    }

    /**
     * Tile for the player.
     */
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

    /**
     * Tiles for mobs.
     */
    object Mobs {
        /**
         * Tile for the boss.
         */
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

        /**
         * Tile for an impl.
         */
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

        /**
         * Tile for a goblin.
         */
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

        /**
         * Tile for a tiny zombie.
         */
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

        /**
         * Tile for a skeleton.
         */
        object Skeleton {
            val idle = SpriteAnimation(
                spriteMap = dungeonTileset2,
                spriteWidth = 16,
                spriteHeight = 16,
                marginTop = 80,
                marginLeft = 368,
                columns = 4
            )
        }

        /**
         * Tile for a necromancer.
         */
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

    /**
     * Tiles for interface
     */
    object Interface {
        object Heart {
            val full = SpriteAnimation(
                spriteMap = dungeonTileset2,
                spriteWidth = 13,
                spriteHeight = 12,
                marginLeft = 289,
                marginTop = 258
            )

            val half = SpriteAnimation(
                spriteMap = dungeonTileset2,
                spriteWidth = 13,
                spriteHeight = 12,
                marginLeft = 305,
                marginTop = 258
            )

            val empty = SpriteAnimation(
                spriteMap = dungeonTileset2,
                spriteWidth = 13,
                spriteHeight = 12,
                marginLeft = 321,
                marginTop = 258
            )
        }
    }
}
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
    private val gui1 = runBlocking {
        resourcesVfs["gui1.png"].readBitmap()
    }

    private val kyrises = runBlocking {
        resourcesVfs["Kyrises_Free_16x16_RPG_Icon_Pack.png"].readBitmap()
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
        object Damage {
            val icon = SpriteAnimation(
                spriteMap = gui1,
                spriteWidth = 36,
                spriteHeight = 36,
                marginLeft = 319,
                marginTop = 256
            )
        }

        object Experience {
            val levelIcon = SpriteAnimation(
                spriteMap = gui1,
                spriteWidth = 36,
                spriteHeight = 36,
                marginLeft = 415,
                marginTop = 256
            )
        }

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

        /**
         * Tiles for cells in the inventory for items.
         */
        object ItemPlace {
            val weaponsIcon = SpriteAnimation(
                spriteMap = kyrises,
                spriteWidth = 16,
                spriteHeight = 16,
                marginLeft = 175,
                marginTop = 159,
            )

            val armorsIcon = SpriteAnimation(
                spriteMap = kyrises,
                spriteWidth = 16,
                spriteHeight = 16,
                marginLeft = 95,
                marginTop = 287,
            )

            val poisonsIcon = SpriteAnimation(
                spriteMap = kyrises,
                spriteWidth = 16,
                spriteHeight = 16,
                marginLeft = 128,
                marginTop = 223,
            )

            val chosen = SpriteAnimation(
                spriteMap = dungeonTileset2,
                spriteWidth = 16,
                spriteHeight = 16,
                marginLeft = 32,
                marginTop = 192
            )

            val available = SpriteAnimation(
                spriteMap = dungeonTileset2,
                spriteWidth = 16,
                spriteHeight = 16,
                marginLeft = 64,
                marginTop = 192
            )
        }
    }

    /**
     * Tiles for items (whether in the inventory or on the field).
     */
    object Items {
        object Weapons {
            val sword = SpriteAnimation(
                spriteMap = dungeonTileset2,
                spriteWidth = 16,
                spriteHeight = 32,
                marginLeft = 336,
                marginTop = 24
            )

            val ax = SpriteAnimation(
                spriteMap = dungeonTileset2,
                spriteWidth = 16,
                spriteHeight = 32,
                marginLeft = 336,
                marginTop = 82
            )
        }

        object Potion {
            val blue = SpriteAnimation(
                spriteMap = dungeonTileset2,
                spriteWidth = 10,
                spriteHeight = 11,
                marginLeft = 307,
                marginTop = 228
            )
        }
    }
}
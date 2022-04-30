package ru.hse.sd.rogue.game.logic.level

import java.nio.file.Path
import ru.hse.sd.rogue.game.logic.level.mobsfabric.ClassicDungeonMobsFactory
import ru.hse.sd.rogue.game.logic.level.mobsfabric.MobsFactory
import ru.hse.sd.rogue.game.logic.level.serialization.LevelJsonSerializer


@DslMarker
private annotation class LevelDslMarker

/**
 * DSL to create a new level
 */
fun level(build: LevelBuilder.() -> Level): Level = LevelBuilder().build()

@LevelDslMarker
class LevelBuilder {
    /**
     * Load level from file
     */
    fun load(path: Path): Level {
        with(LevelJsonSerializer) {
            return readFrom(path)
        }
    }

    /**
     * Generate level
     */
    fun generate(init: LevelGeneratorBuilder.() -> Unit): Level =
        LevelGeneratorBuilder().apply(init).build().generate()

    @LevelDslMarker
    class LevelGeneratorBuilder {
        /**
         * Use it to configure map
         */
        val map = MapSettings()

        /**
         * Use it to configure mobs
         */
        val mobs = MobsSettings()

        /**
         * Map settings
         */
        class MapSettings(
            /**
             * Level width
             */
            var width: Int = 64,
            /**
             * Level height
             */
            var height: Int = 64,
            /**
             * Border thickness at the level boundary
             */
            var border: Int = 1,
            /**
             * Minimum room width and height
             */
            var minRoomSize: Int = 5,
            /**
             * The degree of wobbling of corridors between rooms
             */
            var corridorWobbling: Double = 0.1,
            /**
             * Thickness of cordiors
             */
            var corridorThickness: Pair<Int, Int> = 0 to 1,
            /**
             * Number of iterations in space division to generate rooms.
             * The number of rooms will be approximately equal to 2^[splitNumIterations]
             */
            var splitNumIterations: Int = 4
        )

        /**
         * Mobs settings
         */
        data class MobsSettings(
            /**
             * Fabric to generate mobs
             */
            var mobsFactory: MobsFactory = ClassicDungeonMobsFactory(),
            /**
             * Mob frequency
             */
            var probability: Pair<Double, Double> = 0.01 to 0.1
        )

        fun build() = LevelGenerator(
            width = map.width,
            height = map.height,
            border = map.border,
            minRoomSize = map.minRoomSize,
            corridorWobbling = map.corridorWobbling,
            minCorridorThickness = map.corridorThickness.first,
            maxCorridorThickness = map.corridorThickness.second,
            splitNumIterations = map.splitNumIterations,
            mobsFactory = mobs.mobsFactory,
            minMobsProbability = mobs.probability.first,
            maxMobsProbability = mobs.probability.second,
        )
    }
}

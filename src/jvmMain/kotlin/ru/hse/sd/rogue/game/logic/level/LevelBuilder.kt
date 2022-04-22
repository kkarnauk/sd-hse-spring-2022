package ru.hse.sd.rogue.game.logic.level

import ru.hse.sd.rogue.game.logic.level.mobsfabric.ClassicDungeonMobsFabric
import ru.hse.sd.rogue.game.logic.level.mobsfabric.MobsFabric


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
    fun load(path: String): Level {
        TODO("Not yet implemented")
    }

    /**
     * Generate level
     */
    fun generate(init: LevelGeneratorBuilder.() -> Unit): Level =
        LevelGeneratorBuilder().apply(init).build().generate()

    @LevelDslMarker
    class LevelGeneratorBuilder {

        val map = MapSettings()
        val mobs = MobsSettings()

        /**
         * Map settings
         */
        class MapSettings(
            var width: Int = 64,
            var height: Int = 64,
            var border: Int = 1,
            var minRoomSize: Int = 5,
            var corridorWobbling: Double = 0.1,
            var corridorThickness: Pair<Int, Int> = 0 to 1,
            var splitNumIterations: Int = 4
        )

        /**
         * Mobs settings
         */
        data class MobsSettings(
            var mobsFabric: MobsFabric = ClassicDungeonMobsFabric(),
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
            mobsFabric = mobs.mobsFabric,
            minMobsProbability = mobs.probability.first,
            maxMobsProbability = mobs.probability.second,
        )
    }
}

package ru.hse.sd.rogue.game.logic.level


@DslMarker
private annotation class LevelDslMarker


fun level(build: LevelBuilder.() -> Level): Level = LevelBuilder().build()

@LevelDslMarker
class LevelBuilder {
    fun load(path: String): Level {
        TODO("Not yet implemented")
    }

    fun generate(init: LevelGeneratorBuilder.() -> Unit): Level =
        LevelGeneratorBuilder().apply(init).build().generate()

    @LevelDslMarker
    class LevelGeneratorBuilder {

        val map = MapSettings()
        val mobs = MobsSettings()

        class MapSettings(
            var width: Int = 64,
            var height: Int = 64,
            var border: Int = 1,
            var minRoomSize: Int = 5,
            var corridorWobbling: Double = 0.1,
            var corridorThickness: Pair<Int, Int> = 0 to 1,
            var splitNumIterations: Int = 4
        )

        data class MobsSettings(
            var probability: Pair<Double, Double> = 0.01 to 0.5
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
            minMobsProbability = mobs.probability.first,
            maxMobsProbability = mobs.probability.second,
        )
    }
}

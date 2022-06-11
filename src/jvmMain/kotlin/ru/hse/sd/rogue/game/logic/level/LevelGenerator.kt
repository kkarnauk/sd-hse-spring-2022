package ru.hse.sd.rogue.game.logic.level

import ru.hse.sd.rogue.game.logic.cell.CellContent
import ru.hse.sd.rogue.game.logic.characteristics.MutableDamage
import ru.hse.sd.rogue.game.logic.characteristics.MutableHealth
import ru.hse.sd.rogue.game.logic.level.mobsfabric.MobsFactory
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.CellState
import ru.hse.sd.rogue.game.state.InventoryMutableState
import ru.hse.sd.rogue.game.state.character.CharacterMutableState
import ru.hse.sd.rogue.game.state.character.PlayerState
import kotlin.math.roundToInt
import kotlin.math.sign
import kotlin.random.Random

/**
 * Generates a new level
 */
class LevelGenerator(
    private val width: Int,
    private val height: Int,
    private val border: Int,
    private val minRoomSize: Int,
    private val corridorWobbling: Double,
    private val minCorridorThickness: Int,
    private val maxCorridorThickness: Int,
    private val splitNumIterations: Int,
    private val mobsFactory: MobsFactory,
    private val minMobsProbability: Double,
    private val maxMobsProbability: Double,
    private val random: Random
) {

    private class BSP(
        private val width: Int,
        private val height: Int,
        private val border: Int,
        private val random: Random
    ) {

        data class Room(
            val x1: Int,
            val y1: Int,
            val x2: Int,
            val y2: Int
        ) {
            val width = x2 - x1
            val height = y2 - y1
            fun points(): List<Position> =
                (x1 until x2).flatMap { x ->
                    (y1 until y2).map { y ->
                        Position(x, y)
                    }
                }

        }

        private data class Node(
            val region: Room,
            var leftChild: Node? = null,
            var rightChild: Node? = null,
            var roomRegion: Room? = null
        ) {
            fun traverse(): List<Node> {
                return listOf(this) + leftChild?.traverse().orEmpty() + rightChild?.traverse().orEmpty()
            }

            val isLeaf: Boolean
                get() = leftChild == null && rightChild == null
        }

        private val root = Node(
            Room(border, border, width - border, height - border)
        )

        private val leaves: List<Node>
            get() = root.traverse().filter { it.isLeaf }

        private fun Node.canSplitWidth(minRoomSize: Int): Boolean =
            minRoomSize * 2 <= region.width

        private fun Node.canSplitHeight(minRoomSize: Int): Boolean =
            minRoomSize * 2 <= region.height

        private fun Node.canSplit(minRoomSize: Int): Boolean =
            canSplitWidth(minRoomSize) || canSplitHeight(minRoomSize)

        fun split(minRoomSize: Int) {
            leaves.forEach { leaf ->
                if (leaf.canSplit(minRoomSize)) {
                    val (firstRegion, secondRegion) = when {
                        !leaf.canSplitHeight(minRoomSize) || (
                                leaf.canSplitWidth(minRoomSize) && random.nextInt(2) == 0
                                ) -> {
                            val x = random.nextInt(
                                leaf.region.x1 + minRoomSize,
                                leaf.region.x2 - minRoomSize + 1
                            )
                            val left = Room(leaf.region.x1, leaf.region.y1, x, leaf.region.y2)
                            val right = Room(x, leaf.region.y1, leaf.region.x2, leaf.region.y2)
                            left to right
                        }
                        else -> {
                            val y = random.nextInt(
                                leaf.region.y1 + minRoomSize,
                                leaf.region.y2 - minRoomSize + 1
                            )
                            val upper = Room(leaf.region.x1, leaf.region.y1, leaf.region.x2, y)
                            val down = Room(leaf.region.x1, y, leaf.region.x2, leaf.region.y2)
                            upper to down
                        }
                    }
                    leaf.leftChild = Node(firstRegion)
                    leaf.rightChild = Node(secondRegion)
                }
            }
        }

        fun generateRooms(minRoomSize: Int) {
            leaves.forEach {
                val x1 = random.nextInt(it.region.x1, it.region.x2 - minRoomSize + 1)
                val y1 = random.nextInt(it.region.y1, it.region.y2 - minRoomSize + 1)
                val x2 = random.nextInt(x1 + minRoomSize, it.region.x2 + 1)
                val y2 = random.nextInt(y1 + minRoomSize, it.region.y2 + 1)
                it.roomRegion = Room(x1, y1, x2, y2)
            }
        }

        val rooms
            get() = root.traverse().mapNotNull { it.roomRegion }

        private val _corridorPoints = mutableListOf<Position>()
        val corridorPoints: List<Position>
            get() = _corridorPoints

        private fun generateCorridorBetweenPoints(
            x1: Int,
            y1: Int,
            x2: Int,
            y2: Int,
            thickness: Int,
            corridorWobbling: Double
        ) {
            var lastMoveByX = random.nextInt(2) == 0
            var currentX = x1
            var currentY = y1
            while (currentX != x2 || currentY != y2) {
                val dx = (x2 - currentX).sign
                val dy = (y2 - currentY).sign
                val moveByX = when {
                    dx == 0 -> false
                    dy == 0 -> true
                    random.nextDouble() < corridorWobbling -> !lastMoveByX
                    else -> lastMoveByX
                }
                if (moveByX) {
                    currentX += dx
                } else {
                    currentY += dy
                }
                lastMoveByX = moveByX
                IntRange(currentX - thickness, currentX + thickness).forEach { x ->
                    IntRange(currentY - thickness, currentY + thickness).forEach { y ->
                        if (border <= x && x < width - border && border <= y && y < height - border) {
                            _corridorPoints.add(Position(x, y))
                        }
                    }
                }
            }
        }

        private fun generateCorridorBetweenRectangles(
            first: Room,
            second: Room,
            thickness: Int,
            corridorWobbling: Double
        ) {
            generateCorridorBetweenPoints(
                random.nextInt(first.x1, first.x2),
                random.nextInt(first.y1, first.y2),
                random.nextInt(second.x1, second.x2),
                random.nextInt(second.y1, second.y2),
                thickness,
                corridorWobbling
            )
        }

        private fun generateCorridorsInSubtree(
            node: Node,
            minThickness: Int,
            maxThickness: Int,
            corridorWobbling: Double
        ) {
            val left = node.leftChild ?: return
            val right = node.rightChild ?: return

            val leftRooms = left.traverse().mapNotNull { it.roomRegion }
            val rightRooms = right.traverse().mapNotNull { it.roomRegion }

            generateCorridorBetweenRectangles(
                leftRooms.randomOrNull(random) ?: return,
                rightRooms.randomOrNull(random) ?: return,
                random.nextInt(minThickness, maxThickness + 1),
                corridorWobbling
            )

            generateCorridorsInSubtree(left, minThickness, maxThickness, corridorWobbling)
            generateCorridorsInSubtree(right, minThickness, maxThickness, corridorWobbling)
        }

        fun generateCorridors(minThickness: Int, maxThickness: Int, corridorWobbling: Double) {
            generateCorridorsInSubtree(root, minThickness, maxThickness, corridorWobbling)
        }

        fun getCellStates(): List<List<CellState>> {
            val space = rooms.flatMap {
                it.points()
            }.toHashSet() + corridorPoints.toHashSet()
            return (0 until width).map { x ->
                (0 until height).map { y ->
                    val content = if (space.contains(Position(x, y))) {
                        CellContent.Space
                    } else {
                        CellContent.Wall
                    }
                    CellState(Position(x, y), content)
                }
            }
        }
    }

    private fun generateCharacters(rooms: List<BSP.Room>): List<CharacterMutableState> {
        val shuffledRooms = rooms.shuffled()
        val playerRoom = shuffledRooms[0]
        val bossRoom = shuffledRooms[1]
        val mobsRooms = shuffledRooms.drop(2)

        val characterStates = mutableListOf<CharacterMutableState>()

        characterStates.add(
            PlayerState(
                MutableHealth(6),
                playerRoom.points().random(random).asMutable(),
                MutableDamage(3, 5),
                InventoryMutableState()
            )
        )
        characterStates.add(
            mobsFactory.generateBossMob(bossRoom.points().random(random))
        )
        mobsRooms.flatMapTo(characterStates) {
            val mobGenerator = listOf(
                { position: Position -> mobsFactory.generateRandomEasyMob(position) },
                { position: Position -> mobsFactory.generateRandomMediumMob(position) },
                { position: Position -> mobsFactory.generateRandomHardMob(position) },
            ).random()
            val points = it.points()
            val usePoints = Random.nextInt(
                (minMobsProbability * points.size).roundToInt(),
                (maxMobsProbability * points.size).roundToInt()
            )
            points.shuffled().take(usePoints).map { position -> mobGenerator(position) }
        }
        return characterStates
    }

    /**
     * Generates a new level
     */
    fun generate(): Level {
        val bsp = BSP(width, height, border, random)
        repeat(splitNumIterations) {
            bsp.split(minRoomSize)
        }
        bsp.generateRooms(minRoomSize)
        bsp.generateCorridors(minCorridorThickness, maxCorridorThickness, corridorWobbling)
        val characters = generateCharacters(bsp.rooms)
        return Level(bsp.getCellStates(), characters)
    }
}
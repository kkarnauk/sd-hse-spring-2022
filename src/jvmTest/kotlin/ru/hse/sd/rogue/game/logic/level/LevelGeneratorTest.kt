package ru.hse.sd.rogue.game.logic.level

import ru.hse.sd.rogue.game.logic.cell.CellContent
import ru.hse.sd.rogue.game.logic.level.mobsfabric.ClassicDungeonMobsFactory
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.character.PlayerState
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class LevelGeneratorTest {

    private val defaultGenerator = LevelGenerator(
        width = 100,
        height = 100,
        border = 1,
        minRoomSize = 3,
        corridorWobbling = 0.01,
        minCorridorThickness = 1,
        maxCorridorThickness = 2,
        splitNumIterations = 4,
        mobsFactory = ClassicDungeonMobsFactory(),
        minMobsProbability = 0.2,
        maxMobsProbability = 0.5
    )

    @Test
    fun testIsCorrectPositions() {
        repeat(100) {
            val cells = defaultGenerator.generate().cells
            assertEquals(100, cells.size)
            assertEquals(100, cells[0].size)
            assertTrue(cells.isNotEmpty())
            cells.forEach { line ->
                assertEquals(cells.size, line.size)
            }
            cells.forEachIndexed { x, yLine ->
                yLine.forEachIndexed { y, cellState ->
                    val requirePosition = Position(x, y)
                    assertEquals(requirePosition, cellState.position)
                }
            }
        }
    }

    @Test
    fun testIsConnected() {
        repeat(100) {
            val cells = defaultGenerator.generate().cells
            val start = cells.flatten().first { it.content == CellContent.Space }.position
            val queue = ArrayDeque(listOf(start))
            val visited = List(cells.size) { List(cells[it].size) { false }.toMutableList() }
            visited[start.x][start.y] = true
            while (queue.isNotEmpty()) {
                val current = queue.removeFirst()
                listOf(
                    current + Position(1, 0),
                    current + Position(-1, 0),
                    current + Position(0, 1),
                    current + Position(0, -1),
                ).filter {
                    it.x >= 0 && it.y >= 0 && it.x < cells.size && it.y < cells[0].size
                }.filter {
                    cells[it.x][it.y].content == CellContent.Space
                }.filterNot {
                    visited[it.x][it.y]
                }.forEach {
                    visited[it.x][it.y] = true
                    queue.add(it)
                }
            }
            val actualVisited = cells.flatten().filter { it.content == CellContent.Space }.map { it.position }
            actualVisited.forEach {
                assertTrue(visited[it.x][it.y])
            }
            assertEquals(visited.flatten().count { it }, actualVisited.size)
        }
    }

    @Test
    fun testIsCorrectCharacters() {
        repeat(100) {
            val level = defaultGenerator.generate()
            assertEquals(1, level.characters.filterIsInstance<PlayerState>().size)
            level.characters
                .map { it.position }
                .map { level.cells[it.x][it.y] }
                .forEach { assertEquals(CellContent.Space, it.content) }
            assertEquals(level.characters.size, level.characters.map { it.position }.distinct().size)
        }
    }

    @Test
    fun testBorder() {
        repeat(100) {
            val level = defaultGenerator.generate()
            val cells = level.cells
            cells.flatten()
                .filter {
                    it.position.x == 0 ||
                            it.position.y == 0 ||
                            it.position.x == cells.size - 1 ||
                            it.position.y == cells[0].size - 1
                }.forEach {
                    assertEquals(CellContent.Wall, it.content)
                }
        }
    }
}
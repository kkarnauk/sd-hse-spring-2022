package ru.hse.sd.rogue.game.state

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

private class MockState : VersionableState() {
    fun forceUpdate() = updateVersion()
}

class VersionableStateTest {
    @Test
    fun `test versions`() {
        val state = MockState()
        val version1 = state.version
        val version2 = state.version
        assertFalse(state.hasChangedSince(version1))
        assertFalse(state.hasChangedSince(version2))

        state.forceUpdate()
        val version3 = state.version
        assertTrue(state.hasChangedSince(version1))
        assertTrue(state.hasChangedSince(version2))
        assertFalse(state.hasChangedSince(version3))
    }
}

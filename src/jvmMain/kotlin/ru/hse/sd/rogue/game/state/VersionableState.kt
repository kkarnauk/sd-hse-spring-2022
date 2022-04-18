package ru.hse.sd.rogue.game.state

/**
 * Represent a version of something.
 */
@Suppress("unused")
@JvmInline
value class Version(val version: Long)

/**
 * Marks [State] that this one can say whether it has been changed or not since some version.
 */
abstract class VersionableState : State {
    private var myVersion: Long = 0

    protected fun updateVersion() {
        myVersion++
    }

    /**
     * Current version of the state.
     */
    val version: Version
        get() = Version(myVersion)

    /**
     * Whether this state has been changed since [past].
     */
    fun hasChangedSince(past: Version): Boolean = past != version
}

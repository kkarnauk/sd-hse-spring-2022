package ru.hse.sd.rogue.game.state

@Suppress("unused")
@JvmInline
value class Version(val version: Long)

abstract class VersionableState : State {
    private var myVersion: Long = 0

    protected fun updateVersion() {
        myVersion++
    }

    val version: Version
        get() = Version(myVersion)

    fun hasChangedSince(past: Version): Boolean = past != version
}

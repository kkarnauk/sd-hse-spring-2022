package ru.hse.sd.rogue.game.logic.common

/**
 * Represents an id of an object.
 * For example, can be used to implement `equals` or `hashCode`.
 */
class Id private constructor(val id: Long) {
    override fun equals(other: Any?): Boolean {
        return this === other || other is Id && id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        private var myId = 0L

        /**
         * Creates a new [Id].
         */
        fun create(): Id = Id(myId++)
    }
}

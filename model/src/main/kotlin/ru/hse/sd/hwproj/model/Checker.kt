package ru.hse.sd.hwproj.model

/**
 * Represents a checker for a submission that can be loaded by a professor.
 */
data class Checker(
    /**
     * Unique id for the checker.
     */
    val id: Int,
    /**
     * [Content] of the checker.
     */
    val content: Content
) {
    /**
     * Contains information about a program that will be invoked to check a submission.
     */
    class Content(
        /**
         * Bytes of the program.
         */
        val bytes: ByteArray
    )
}

fun main() {
    println("echo ok".toByteArray().toList())
}

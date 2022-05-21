package ru.hse.sd.hwproj.model

import java.sql.Timestamp

/**
 * Represents a homework that can be loaded by a professor and be accomplished by a student.
 */
data class Homework(
    /**
     * Unique id for the homework.
     */
    val id: Long,
    /**
     * [Content] for the homework.
     */
    val content: Content
) {
    /**
     * Represents common information about the homework.
     */
    data class Content(
        /**
         * Name of a homework.
         */
        val name: String,
        /**
         * Time when this homework is published.
         */
        val startDate: Timestamp,
        /**
         * Deadline of the homework.
         */
        val endDate: Timestamp,
        /**
         * Statement for the homework.
         */
        val statement: String,
        /**
         * Unique id of a checker that is going to be invoked when this homework is done.
         */
        val checkerId: Long
    )
}

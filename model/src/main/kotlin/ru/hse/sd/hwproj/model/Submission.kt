package ru.hse.sd.hwproj.model

import java.net.URL
import java.sql.Timestamp

/**
 * Represents information about a submission for a homework.
 */
data class Submission(
    /**
     * Unique id of the submission.
     */
    val id: Long,
    /**
     * [Content] for the submission.
     */
    val content: Content
) {
    /**
     * Represents detailed information about a submission.
     */
    data class Content(
        /**
         * ID of submitted homework.
         */
        val homeworkId: Long,
        /**
         * Date of submission.
         */
        val date: Timestamp,
        /**
         * Link to solution to the homework.
         */
        val solutionLink: URL
    )

    /**
     * Represents a result for a submission.
     */
    data class Result(
        /**
         * Whether the submission is successful.
         */
        val success: Boolean,
        /**
         * Some additional information about the submission.
         */
        val message: String,
        /**
         * Date when the submission is checked.
         */
        val checkDate: Timestamp
    )
}

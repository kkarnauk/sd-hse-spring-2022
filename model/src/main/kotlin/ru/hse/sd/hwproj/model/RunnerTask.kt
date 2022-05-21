package ru.hse.sd.hwproj.model

/**
 * Represents a task that is invoked when a checker is going to test a submission.
 */
data class RunnerTask(
    /**
     * Checker to be used.
     */
    val checkerId: Long,
    /**
     * Submission to be checked.
     */
    val submissionId: Long
)

package ru.hse.sd.repo

import ru.hse.sd.hwproj.model.Checker
import ru.hse.sd.hwproj.model.Homework
import ru.hse.sd.hwproj.model.Submission

/**
 * Stores all the information about current state.
 */
interface Repository {
    /**
     * @return [Homework.Content] by id.
     */
    suspend fun getHomework(id: Long): Homework.Content

    /**
     * @return all [Homework].
     */
    suspend fun getHomeworks(): List<Homework>

    /**
     * Adds a new [Homework]. Generates new id for that.
     * @return [Homework] with generated id and [content].
     */
    suspend fun addHomework(content: Homework.Content): Homework

    /**
     * @return [Submission] by id.
     */
    suspend fun getSubmission(id: Long): Submission.Content

    /**
     * @return [Submission.Result] by submission id.
     */
    suspend fun getSubmissionResult(submissionId: Long): Submission.Result

    /**
     * Adds a new [Submission.Result].
     */
    suspend fun addSubmissionResult(submissionId: Long, result: Submission.Result)

    /**
     * @return all [Submission] for homework id.
     */
    suspend fun getSubmissions(homeworkId: Long): List<Submission>

    /**
     * Adds a new [Submission]. Generates new id for that.
     * @return [Submission] with generated id and [content].
     */
    suspend fun addSubmission(content: Submission.Content): Submission

    /**
     * @return [Checker.Content] by id.
     */
    suspend fun getChecker(id: Long): Checker.Content

    /**
     * Adds a new [Checker]. Generates new id for that.
     * @return [Checker] with generated id and [content].
     */
    suspend fun addChecker(content: Checker.Content): Checker
}

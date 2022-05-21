package ru.hse.sd.repo

import ru.hse.sd.hwproj.model.Checker
import ru.hse.sd.hwproj.model.Homework
import ru.hse.sd.hwproj.model.Submission

interface Repository {
    suspend fun getHomework(id: Long): Homework.Content

    suspend fun getHomeworks(): List<Homework>

    suspend fun addHomework(content: Homework.Content): Homework

    suspend fun getSubmission(id: Long): Submission.Content

    suspend fun getSubmissionResult(submissionId: Long): Submission.Result

    suspend fun addSubmissionResult(submissionId: Long, result: Submission.Result)

    suspend fun getSubmissions(homeworkId: Long): List<Submission>

    suspend fun addSubmission(content: Submission.Content): Submission

    suspend fun getChecker(id: Long): Checker.Content

    suspend fun addChecker(content: Checker.Content): Checker
}

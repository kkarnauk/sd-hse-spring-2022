package ru.hse.sd.repo

import ru.hse.sd.hwproj.model.Checker
import ru.hse.sd.hwproj.model.Homework
import ru.hse.sd.hwproj.model.Submission

interface Repository {
    fun getHomework(id: Long): Homework.Content

    fun getHomeworks(): List<Homework>

    fun addHomework(content: Homework.Content): Homework

    fun getSubmission(id: Long): Submission.Content

    fun getSubmissionResult(submissionId: Long): Submission.Result

    fun getSubmissions(homeworkId: Long): List<Submission>

    fun addSubmission(content: Submission.Content): Submission

    fun addChecker(content: Checker.Content): Checker
}

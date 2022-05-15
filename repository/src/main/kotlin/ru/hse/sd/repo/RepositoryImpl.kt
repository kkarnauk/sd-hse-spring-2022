package ru.hse.sd.repo

import ru.hse.sd.hwproj.model.Checker
import ru.hse.sd.hwproj.model.Homework
import ru.hse.sd.hwproj.model.Submission

internal class RepositoryImpl : Repository {
    override fun getHomework(id: Long): Homework.Content {
        TODO("Not yet implemented")
    }

    override fun getHomeworks(): List<Homework> {
        TODO("Not yet implemented")
    }

    override fun getSubmission(id: Long): Submission.Content {
        TODO("Not yet implemented")
    }

    override fun getSubmissionResult(submissionId: Long): Submission.Result {
        TODO("Not yet implemented")
    }

    override fun getSubmissions(homeworkId: Long): List<Submission> {
        TODO("Not yet implemented")
    }

    override fun addHomework(content: Homework.Content): Homework {
        TODO("Not yet implemented")
    }

    override fun addSubmission(content: Submission.Content): Submission {
        TODO("Not yet implemented")
    }

    override fun addChecker(content: Checker.Content): Checker {
        TODO("Not yet implemented")
    }
}

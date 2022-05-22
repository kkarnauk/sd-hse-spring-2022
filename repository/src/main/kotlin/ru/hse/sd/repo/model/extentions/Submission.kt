package ru.hse.sd.repo.model.extentions

import java.net.URL
import java.sql.Timestamp
import org.jetbrains.exposed.sql.ResultRow
import ru.hse.sd.hwproj.model.Submission
import ru.hse.sd.repo.schema.SubmissionResults
import ru.hse.sd.repo.schema.Submissions

internal fun ResultRow.toSubmission(): Submission {
    return Submission(
        id = this[Submissions.id],
        content = this.toSubmissionContent()
    )
}

internal fun ResultRow.toSubmissionContent(): Submission.Content {
    return Submission.Content(
        homeworkId = this[Submissions.homeworkId],
        date = this[Submissions.date].let { Timestamp.from(it) },
        solutionLink = URL(this[Submissions.link]),
    )
}

internal fun ResultRow.toSubmissionResult(): Submission.Result {
    return Submission.Result(
        success = this[SubmissionResults.success],
        message = this[SubmissionResults.message],
        checkDate = this[SubmissionResults.checkDate].let { Timestamp.from(it) }
    )
}

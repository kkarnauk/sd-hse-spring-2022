package ru.hse.sd.repo.schema

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp

object SubmissionResults : Table("submission_results") {
    val id = integer("id").autoIncrement()
    val success = bool("success")
    val message = text("message")
    val checkDate = timestamp("check_date")
    val submissionId = integer("submission_id") references Submissions.id

    override val primaryKey = PrimaryKey(id)
}
package ru.hse.sd.hwproj.model

import java.net.URL
import java.sql.Timestamp

data class Submission(val id: Long, val runnerTask: RunnerTask, val content: Content) {
    data class Content(val date: Timestamp, val solutionLink: URL)

    data class Result(val success: Boolean, val message: String, val checkDate: Timestamp)
}

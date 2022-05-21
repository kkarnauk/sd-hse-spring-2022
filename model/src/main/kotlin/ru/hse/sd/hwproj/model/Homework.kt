package ru.hse.sd.hwproj.model

import java.sql.Timestamp

data class Homework(val id: Long, val content: Content) {
    data class Content(
        val name: String,
        val startDate: Timestamp,
        val endDate: Timestamp,
        val statement: String,
        val checkerId: Long
    )
}

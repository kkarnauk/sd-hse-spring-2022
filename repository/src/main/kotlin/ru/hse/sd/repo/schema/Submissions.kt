package ru.hse.sd.repo.schema

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp

object Submissions : Table("submissions") {
    val id = integer("id").autoIncrement()
    val homeworkId = integer("homework_id") references Homeworks.id
    val date = timestamp("date")
    val link = text("link")

    override val primaryKey = PrimaryKey(id)
}

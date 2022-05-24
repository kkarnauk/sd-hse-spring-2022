package ru.hse.sd.repo.schema

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp

object Homeworks : Table("homeworks") {
    val id = integer("id").autoIncrement()
    val name = text("name")
    val startDate = timestamp("start_date")
    val endDate = timestamp("end_date")
    val statement = text("statement")
    val checkerId = integer("checker_id") references Checkers.id

    override val primaryKey = PrimaryKey(id)
}

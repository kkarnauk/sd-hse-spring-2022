package ru.hse.sd.repo.schema

import org.jetbrains.exposed.sql.Table

object Checkers : Table("checkers") {
    val id = integer("id").autoIncrement()

    override val primaryKey = PrimaryKey(id)
}

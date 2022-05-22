package ru.hse.sd.repo.model.extentions

import java.sql.Timestamp
import org.jetbrains.exposed.sql.ResultRow
import ru.hse.sd.hwproj.model.Homework
import ru.hse.sd.repo.schema.Homeworks

internal fun ResultRow.toHomework(): Homework {
    return Homework(
        id = this[Homeworks.id],
        content = this.toHomeworkContent()
    )
}

internal fun ResultRow.toHomeworkContent(): Homework.Content {
    return Homework.Content(
        name = this[Homeworks.name],
        startDate = this[Homeworks.startDate].let { Timestamp.from(it) },
        endDate = this[Homeworks.endDate].let { Timestamp.from(it) },
        statement = this[Homeworks.statement],
        checkerId = this[Homeworks.checkerId]
    )
}

package org.example.domain.db
import org.example.domain.db.Activities.references
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp

object Sleeps : Table("sleeps") {
    val id = integer("id").autoIncrement()
    val sleepStart = varchar("sleep_start",255)
    val sleepEnd = varchar("sleep_end",255)
    val sleepDuration = double("sleep_duration")
    val bedtimeReminder = bool("bedtime_reminder")
    val userId = integer("user_id").references(Users.id, onDelete = ReferenceOption.CASCADE)

    override val primaryKey = PrimaryKey(id, name = "PK_Sleep_ID")
}
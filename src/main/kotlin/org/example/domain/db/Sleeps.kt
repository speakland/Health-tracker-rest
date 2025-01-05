package org.example.domain.db
import org.example.domain.db.Activities.references
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.core.JsonProcessingException;
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.jodatime.datetime


object Sleeps : Table("sleeps") {
    val id = integer("id").autoIncrement()
    val sleepStart = datetime("sleep_start")
    val sleepEnd = datetime("sleep_end")
    val sleepDuration = double("sleep_duration")
    val bedtimeReminder = bool("bedtime_reminder")
    val userId = integer("user_id").references(Users.id, onDelete = ReferenceOption.CASCADE)

    override val primaryKey = PrimaryKey(id, name = "PK_Sleep_ID")
}
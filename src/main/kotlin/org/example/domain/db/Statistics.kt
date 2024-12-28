package org.example.domain.db
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp


object Statistics : Table("statistics") {
    val id = integer("stat_id").autoIncrement()
    val totalSleepHours = double("total_sleep_hours")
    val averageCalories = integer("average_calories")
    val totalActivityHours = double("total_activity_hours")
    val weekStart = timestamp("week_start")
    val weekEnd = timestamp("week_end")
    val userId = integer("user_id").references(Users.id, onDelete = ReferenceOption.CASCADE)

    override val primaryKey = PrimaryKey(id, name = "PK_Statistics_ID")
}
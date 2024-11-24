package org.example.domain.db
import org.example.domain.db.Activities.references
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp
import org.jetbrains.exposed.sql.jodatime.date

object Statistics : Table("statistics") {
    val id = integer("id").autoIncrement()
    val totalSleepHours = double("total_sleepHours")
    val averageCalories = integer("average_calories")
    val totalActivityHours = double("total_activityHours")
    val weekStart = timestamp("week_start")
    val weekEnd = timestamp("week_end")
    val userId = integer("user_id").references(Users.id, onDelete = ReferenceOption.CASCADE)

    override val primaryKey = PrimaryKey(id, name = "PK_Statistics_ID")
}
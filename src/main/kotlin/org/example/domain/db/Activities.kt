package org.example.domain.db

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime


object Activities : Table("activity") {
    val id = integer("activity_id").autoIncrement()
    val description = varchar("activity_type", 100)
    val duration = double("duration")
    val calories = integer("calories_burned")
    val started = datetime("started")
    val userId = integer("user_id").references(Users.id, onDelete = ReferenceOption.CASCADE)

    override val primaryKey = PrimaryKey(Activities.id, name = "PK_Activities_ID")
}
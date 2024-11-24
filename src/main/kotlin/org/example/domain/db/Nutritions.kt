package org.example.domain.db
import org.example.domain.db.Activities.references
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.date


// SRP - Responsibility is to manage one user.
//       Database wise, this is the table object.

object Nutritions : Table("nutrition") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 100)
    val calories = integer("calories")
    val fat = double("fat")
    val carbs = double("carbs")
    val protein = double("protein")
    val waterIntake = double("waterIntake")
    val loggedDate = date("loggedDate")
    val userId = integer("user_id").references(Users.id, onDelete = ReferenceOption.CASCADE)

    override val primaryKey = PrimaryKey(id, name = "PK_Nutrition_ID")
}
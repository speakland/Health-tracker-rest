package org.example.domain.db
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.date




object Nutritions : Table("nutritions") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 100)
    val calories = integer("calories")
    val fat = double("fat")
    val carbs = double("carbs")
    val protein = double("protein")
    val waterIntake = double("water_intake")
    val loggedDate = date("logged_date")
    val userId = integer("user_id").references(Users.id, onDelete = ReferenceOption.CASCADE)

    override val primaryKey = PrimaryKey(id, name = "PK_Nutrition_ID")
}
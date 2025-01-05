package org.example.domain.repository
import org.example.domain.Nutrition
import org.example.domain.db.Nutritions
import org.example.utils.mapToNutrition
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class NutritionDAO {

    //Get all the nutritions in the database regardless of user id
    fun getAll(): ArrayList<Nutrition> {
        val nutritionsList: ArrayList<Nutrition> = arrayListOf()
        transaction {
            Nutritions.selectAll().map {
                nutritionsList.add(mapToNutrition(it))
            }
        }
        return nutritionsList
    }


    //Find all nutritions for a specific user id
    fun findByUserId(userId: Int): List<Nutrition> {
        return transaction {
            Nutritions
                .selectAll().where { Nutritions.userId eq userId }
                .map { mapToNutrition(it) }
        }
    }

    //Save a nutrition to the database
    fun save(nutrition: Nutrition) {
        transaction {
            Nutritions.insert {
                it[id] = nutrition.id
                it[name] = nutrition.name
                it[calories] = nutrition.calories
                it[fat] = nutrition.fat
                it[carbs] = nutrition.carbs
                it[protein] = nutrition.protein
                it[waterIntake] = nutrition.waterIntake
                it[loggedDate] = nutrition.loggedDate
                it[userId] = nutrition.userId
            }
        }
    }
}
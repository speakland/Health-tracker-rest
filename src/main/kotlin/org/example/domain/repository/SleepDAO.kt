package org.example.domain.repository
import org.example.domain.Nutrition
import org.example.domain.Sleep
import org.example.domain.db.Nutritions
import org.example.domain.db.Sleeps
import org.example.utils.mapToSleep
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class SleepDAO {
    //Get all the sleep in the database regardless of user id
    fun getAll(): ArrayList<Sleep> {
        val sleepsList: ArrayList<Sleep> = arrayListOf()
        transaction {
            Sleeps.selectAll().map {
                sleepsList.add(mapToSleep(it))
            }
        }
        return sleepsList
    }


    //Find all sleep for a specific user id
    fun findByUserId(userId: Int): List<Sleep> {
        return transaction {
            Sleeps.selectAll().where { Sleeps.userId eq userId }
                .map { mapToSleep(it) }
        }
    }

    //Save a sleep to the database
    fun save(sleep: Sleep) {
        transaction {
            Sleeps.insert {
                it[Sleeps.id] = sleep.id
                it[Sleeps.sleepStart] = sleep.sleepStart
                it[Sleeps.sleepEnd] = sleep.sleepEnd
                it[Sleeps.sleepDuration] = sleep.sleepDuration
                it[Sleeps.bedtimeReminder] = sleep.bedtimeReminder
                it[Sleeps.userId] = sleep.userId
            }
        }
    }
}
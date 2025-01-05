package org.example.domain.repository
import org.example.domain.Nutrition
import org.example.domain.Sleep
import org.example.domain.db.Nutritions
import org.example.domain.db.Sleeps
import org.example.utils.mapToSleep
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.LocalDate;

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
                it[id] = sleep.id
                it[sleepStart] = sleep.sleepStart
                it[sleepEnd] = sleep.sleepEnd
                it[sleepDuration] = sleep.sleepDuration
                it[bedtimeReminder] = sleep.bedtimeReminder
                it[userId] = sleep.userId
            }
        }
    }
}
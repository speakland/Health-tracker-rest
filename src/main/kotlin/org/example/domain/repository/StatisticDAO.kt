package org.example.domain.repository

import org.example.domain.Sleep
import org.example.domain.Statistic
import org.example.domain.db.Nutritions
import org.example.domain.db.Sleeps
import org.example.domain.db.Statistics
import org.example.utils.mapToSleep
import org.example.utils.mapToStatistic
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class StatisticDAO {
    //Get all the statistic in the database regardless of user id
    fun getAll(): ArrayList<Statistic> {
        val statisticsList: ArrayList<Statistic> = arrayListOf()
        transaction {
            Statistics.selectAll().map {
                statisticsList.add(mapToStatistic(it))
            }
        }
        return statisticsList
    }


    //Find all statistic for a specific user id
    fun findByUserId(userId: Int): List<Statistic> {
        return transaction {
            Statistics.selectAll().where { Statistics.userId eq userId }
                .map { mapToStatistic(it) }
        }
    }

    //Save a statistic to the database
    fun save(statistic: Statistic) {
        transaction {
            Statistics.insert {
                it[Statistics.id] = statistic.id
                it[Statistics.totalSleepHours] = statistic.totalSleepHours
                it[Statistics.averageCalories] = statistic.averageCalories
                it[Statistics.totalActivityHours] = statistic.totalActivityHours
                it[Statistics.weekStart] = statistic.weekStart
                it[Statistics.weekEnd] = statistic.weekEnd
                it[Statistics.userId] = statistic.userId
            }
        }
    }
}
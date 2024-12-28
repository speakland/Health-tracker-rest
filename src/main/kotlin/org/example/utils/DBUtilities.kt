package org.example.utils

import org.example.domain.*
import org.example.domain.db.*
import org.jetbrains.exposed.sql.ResultRow

fun mapToUser(it: ResultRow) = User(
    id = it[Users.id],
    name = it[Users.name],
    email = it[Users.email]
)


fun mapToActivity(it: ResultRow) = Activity(
    id = it[Activities.id],
    description = it[Activities.description],
    duration = it[Activities.duration],
    started = it[Activities.started],
    calories = it[Activities.calories],
    userId = it[Activities.userId]
)

fun mapToNutrition(it: ResultRow) = Nutrition(
    id = it[Nutritions.id],
    name = it[Nutritions.name],
    calories = it[Nutritions.calories],
    fat = it[Nutritions.fat],
    carbs = it[Nutritions.carbs],
    protein = it[Nutritions.protein],
    waterIntake = it[Nutritions.waterIntake],
    loggedDate = it[Nutritions.loggedDate],
    userId = it[Nutritions.userId]
)

fun mapToSleep(it: ResultRow) = Sleep(
    id = it[Sleeps.id],
    sleepStart = it[Sleeps.sleepStart],
    sleepEnd = it[Sleeps.sleepEnd],
    sleepDuration = it[Sleeps.sleepDuration],
    bedtimeReminder = it[Sleeps.bedtimeReminder],
    userId = it[Sleeps.userId]
)


fun mapToStatistic(it: ResultRow) = Statistic(
    id = it[Statistics.id],
    totalSleepHours = it[Statistics.totalSleepHours],
    averageCalories = it[Statistics.averageCalories],
    totalActivityHours = it[Statistics.totalActivityHours],
    weekStart = it[Statistics.weekStart],
    weekEnd = it[Statistics.weekEnd],
    userId = it[Statistics.userId],
)
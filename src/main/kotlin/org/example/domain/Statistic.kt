package org.example.domain

import java.time.Instant

data class Statistic(
    var id: Int,
    var totalSleepHours: Double,
    var averageCalories: Int,
    var totalActivityHours: Double,
    var weekStart: String,
    var weekEnd: String,
    var userId: Int)
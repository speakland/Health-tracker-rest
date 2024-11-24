package org.example.domain

import java.time.Instant

data class Statistic(
    var id: Int,
    var totalSleepHours: Double,
    var averageCalories: Int,
    var totalActivityHours: Double,
    var weekStart: Instant,
    var weekEnd: Instant,
    var userId: Int)
package org.example.domain

import org.joda.time.DateTime


data class Nutrition(
    var id: Int,
    var name:String,
    var calories: Int,
    var fat: Double,
    var carbs: Double,
    var protein: Double,
    var waterIntake: Double,
    var loggedDate: DateTime,
    var userId: Int)
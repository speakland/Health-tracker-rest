package org.example.domain
import org.joda.time.DateTime
import org.joda.time.LocalDate
import java.util.*

data class Activity (var id: Int,
                     var description:String,
                     var duration: Double,
                     var calories: Int,
                     var started: DateTime,
                     var userId: Int)
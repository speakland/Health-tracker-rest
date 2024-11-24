package org.example.domain
import java.time.Instant

data class Sleep(
    var id: Int,
    var sleepStart: Instant,
    var sleepEnd: Instant,
    var sleepDuration: Double,
    var bedtimeReminder: Boolean,
    var userId: Int)
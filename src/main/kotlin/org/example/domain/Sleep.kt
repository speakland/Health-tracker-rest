package org.example.domain
import org.joda.time.DateTime
import java.time.Instant

import java.util.*

data class Sleep(
    var id: Int,
    var sleepStart: DateTime,
    var sleepEnd: DateTime,
    var sleepDuration: Double,
    var bedtimeReminder: Boolean,
    var userId: Int)
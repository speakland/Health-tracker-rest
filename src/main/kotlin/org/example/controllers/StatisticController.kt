package org.example.controllers

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.javalin.http.Context
import org.example.domain.Sleep
import org.example.domain.Statistic
import org.example.domain.repository.SleepDAO
import org.example.domain.repository.StatisticDAO
import org.example.domain.repository.UserDAO
import org.example.utils.jsonToObject

object StatisticController {
    private val statisticDao = StatisticDAO()
    private val userDao = UserDAO()
    //--------------------------------------------------------------
    // SleepDAO specifics
    //-------------------------------------------------------------

    fun getAllStatistics(ctx: Context) {
        //mapper handles the deserialization of Joda date into a String.
        val mapper = jacksonObjectMapper()
            .registerModule(JodaModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        ctx.json(mapper.writeValueAsString( statisticDao.getAll() ))
    }

    fun getStatisticsByUserId(ctx: Context) {
        if (userDao.findById(ctx.pathParam("user-id").toInt()) != null) {
            val statistics = statisticDao.findByUserId(ctx.pathParam("user-id").toInt())
            if (statistics.isNotEmpty()) {
                //mapper handles the deserialization of Joda date into a String.
                val mapper = jacksonObjectMapper()
                    .registerModule(JodaModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                ctx.json(mapper.writeValueAsString(statistics))
            }
        }
    }

    fun addStatistic(ctx: Context) {
        //mapper handles the serialisation of Joda date into a String.
        val statistic: Statistic = jsonToObject(ctx.body())
        statisticDao.save(statistic)
        ctx.json(statistic)
    }




}
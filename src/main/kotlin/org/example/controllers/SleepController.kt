package org.example.controllers

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.javalin.http.Context
import org.example.domain.Sleep
import org.example.domain.repository.SleepDAO
import org.example.domain.repository.UserDAO
import org.example.utils.jsonToObject


object SleepController {
    private val sleepDao = SleepDAO()
    private val userDao = UserDAO()
    //--------------------------------------------------------------
    // SleepDAO specifics
    //-------------------------------------------------------------

    fun getAllSleeps(ctx: Context) {
        //mapper handles the deserialization of Joda date into a String.
        val mapper = jacksonObjectMapper()
            .registerModule(JodaModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        ctx.json(mapper.writeValueAsString( sleepDao.getAll() ))
    }

    fun getSleepsByUserId(ctx: Context) {
        if (userDao.findById(ctx.pathParam("user-id").toInt()) != null) {
            val sleeps = sleepDao.findByUserId(ctx.pathParam("user-id").toInt())
            if (sleeps.isNotEmpty()) {
                //mapper handles the deserialization of Joda date into a String.
                val mapper = jacksonObjectMapper()
                    .registerModule(JodaModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                ctx.json(mapper.writeValueAsString(sleeps))
            }
        }
    }

    fun addSleep(ctx: Context) {
        //mapper handles the serialisation of Joda date into a String.
        val sleep: Sleep = jsonToObject(ctx.body())
        sleepDao.save(sleep)
        ctx.json(sleep)
    }




}
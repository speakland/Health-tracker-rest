package org.example.controllers

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.joda.JodaModule
import io.javalin.http.Context
import org.example.domain.repository.UserDAO
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.example.domain.Activity
import org.example.domain.User
import org.example.domain.repository.ActivityDAO

object HealthTrackerController {

    private val userDao = UserDAO()
    private val activityDAO = ActivityDAO()

    fun getAllUsers(ctx: Context) {
        ctx.json(userDao.getAll())
    }


    fun getUserByUserId(ctx: Context) {
        val user = userDao.findById(ctx.pathParam("user-id").toInt())
        if (user != null) {
            ctx.json(user)
        }
    }

    fun getUserByEmail(ctx: Context){
        val user = userDao.findByEmail(ctx.pathParam("Email"))
        if (user != null) {
            ctx.json(user)
        }
    }

    fun addUser(ctx: Context) {
        val mapper = jacksonObjectMapper()
        val user = mapper.readValue<User>(ctx.body())
        userDao.save(user)
        ctx.json(user)
    }

    fun deleteUser(ctx: Context){
        userDao.delete(ctx.pathParam("user-id").toInt())
    }

    fun updateUser(ctx: Context){
        val mapper = jacksonObjectMapper()
        val userUpdates = mapper.readValue<User>(ctx.body())
        userDao.update(
            id = ctx.pathParam("user-id").toInt(),
            user=userUpdates)
    }


    //--------------------------------------------------------------
    // ActivityDAO specifics
    //-------------------------------------------------------------

    fun getAllActivities(ctx: Context) {
        //mapper handles the deserialization of Joda date into a String.
        val mapper = jacksonObjectMapper()
            .registerModule(JodaModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        ctx.json(mapper.writeValueAsString( activityDAO.getAll() ))
    }

    fun getActivitiesByUserId(ctx: Context) {
        if (userDao.findById(ctx.pathParam("user-id").toInt()) != null) {
            val activities = activityDAO.findByUserId(ctx.pathParam("user-id").toInt())
            if (activities.isNotEmpty()) {
                //mapper handles the deserialization of Joda date into a String.
                val mapper = jacksonObjectMapper()
                    .registerModule(JodaModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                ctx.json(mapper.writeValueAsString(activities))
            }
        }
    }

    fun addActivity(ctx: Context) {
        //mapper handles the serialisation of Joda date into a String.
        val mapper = jacksonObjectMapper()
            .registerModule(JodaModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        val activity = mapper.readValue<Activity>(ctx.body())
        activityDAO.save(activity)
        ctx.json(activity)
    }
}
package org.example.controllers
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.joda.JodaModule
import io.javalin.http.Context
import org.example.domain.repository.UserDAO
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.example.domain.Activity
import org.example.domain.repository.ActivityDAO
import org.example.utils.jsonToObject



object ActivityController {

    private val userDao = UserDAO()
    private val activityDao = ActivityDAO()
    //--------------------------------------------------------------
    // ActivityDAO specifics
    //-------------------------------------------------------------


    fun getAllActivities(ctx: Context) {
        val activities = activityDao.getAll()
        if (activities.size != 0) {
            ctx.status(200)
        }
        else{
            ctx.status(404)
        }
        ctx.json(activities)
    }

    fun getActivitiesByUserId(ctx: Context) {
        if (userDao.findById(ctx.pathParam("user-id").toInt()) != null) {
            val activities = activityDao.findByUserId(ctx.pathParam("user-id").toInt())
            if (activities.isNotEmpty()) {
                ctx.json(activities)
                ctx.status(200)
            }
            else{
                ctx.status(404)
            }
        }
        else{
            ctx.status(404)
        }
    }

    fun getActivitiesByActivityId(ctx: Context) {
        val activity = activityDao.findByActivityId((ctx.pathParam("activity-id").toInt()))
        if (activity != null){
            ctx.json(activity)
            ctx.status(200)
        }
        else{
            ctx.status(404)
        }
    }

    fun addActivity(ctx: Context) {
        val activity : Activity = jsonToObject(ctx.body())
        val userId = userDao.findById(activity.userId)
        if (userId != null) {
            val activityId = activityDao.save(activity)
            activity.id = activityId
            ctx.json(activity)
            ctx.status(201)
        }
        else{
            ctx.status(404)
        }
    }

    fun deleteActivityByActivityId(ctx: Context){
        if (activityDao.deleteByActivityId(ctx.pathParam("activity-id").toInt()) != 0)
            ctx.status(204)
        else
            ctx.status(404)
    }

    fun deleteActivityByUserId(ctx: Context){
        if (activityDao.deleteByUserId(ctx.pathParam("user-id").toInt()) != 0)
            ctx.status(204)
        else
            ctx.status(404)
    }

    fun updateActivity(ctx: Context){
        val activity : Activity = jsonToObject(ctx.body())
        if (activityDao.updateByActivityId(
                activityId = ctx.pathParam("activity-id").toInt(),
                activityToUpdate = activity) != 0)
            ctx.status(204)
        else
            ctx.status(404)
    }

}


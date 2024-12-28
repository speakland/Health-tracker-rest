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
    private val activityDAO = ActivityDAO()
    //--------------------------------------------------------------
    // ActivityDAO specifics
    //-------------------------------------------------------------

    /*fun getAllActivities(ctx: Context) {
        //mapper handles the deserialization of Joda date into a String.
        val mapper = jacksonObjectMapper()
            .registerModule(JodaModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        ctx.json(mapper.writeValueAsString( activityDAO.getAll() ))
    }*/

    fun getAllActivities(ctx: Context) {
        try {
            val activities = activityDAO.getAll()
            println("Activities from DAO: $activities")

            if (activities.isEmpty()) {
                ctx.status(404).json("No activities found")
            } else {
                ctx.status(200).json(activities)
            }
        } catch (e: Exception) {
            println("Error fetching activities: ${e.message}")
            e.printStackTrace()
            ctx.status(500).json("Server error: ${e.message}")
        }
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
        val activity: Activity = jsonToObject(ctx.body())
        activityDAO.save(activity)
        ctx.json(activity)
    }



    fun deleteActivityByActivityId(ctx: Context){
        activityDAO.delete(ctx.pathParam("activity-id").toInt())
    }


    fun deleteAllActivitiesByUser(ctx: Context){
        val userId = ctx.pathParam("user-id").toInt()
        activityDAO.deleteAllByUserId(userId)
    }


    fun getActivityById(ctx: Context){
        val activity = activityDAO.findByActivityId(ctx.pathParam("activity-id").toInt())
        if (activity != null) {
            ctx.json(activity)
        }
    }

    fun updateActivityById(ctx: Context){
        val activityUpdates: Activity = jsonToObject(ctx.body())
        activityDAO.update(
            id = ctx.pathParam("activity-id").toInt(),
            activity=activityUpdates)
    }
}


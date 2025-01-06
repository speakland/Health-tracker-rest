package org.example.controllers
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
        val sleeps = sleepDao.getAll()
        if (sleeps.size != 0) {
            ctx.status(200)
        }
        else{
            ctx.status(404)
        }
        ctx.json(sleeps)
    }


    fun getSleepsBySleepId(ctx: Context) {
        val sleep = sleepDao.findBySleepId(ctx.pathParam("sleep-id").toInt())
        if (sleep != null){
            ctx.json(sleep)
            ctx.status(200)
        }
        else{
            ctx.status(404)
        }
    }

    fun getSleepsByUserId(ctx: Context) {
        if (userDao.findById(ctx.pathParam("user-id").toInt()) != null) {
            val sleeps = sleepDao.findByUserId(ctx.pathParam("user-id").toInt())
            if (sleeps.isNotEmpty()) {
                ctx.json(sleeps)
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



    fun addSleep(ctx: Context) {
        val sleep : Sleep = jsonToObject(ctx.body())
        val userId = userDao.findById(sleep.userId)
        if (userId != null) {
            val sleepId = sleepDao.save(sleep)
            sleep.id = sleepId
            ctx.json(sleep)
            ctx.status(201)
        }
        else{
            ctx.status(404)
        }
    }


    fun deleteSleepBySleepId(ctx: Context){
        if (sleepDao.deleteBySleepId(ctx.pathParam("sleep-id").toInt()) != 0)
            ctx.status(204)
        else
            ctx.status(404)
    }

    fun deleteSleepByUserId(ctx: Context){
        if (sleepDao.deleteByUserId(ctx.pathParam("user-id").toInt()) != 0)
            ctx.status(204)
        else
            ctx.status(404)
    }

    fun updateSleep(ctx: Context){
        val sleep : Sleep = jsonToObject(ctx.body())
        if (sleepDao.updateBySleepId(
                sleepId = ctx.pathParam("sleep-id").toInt(),
                sleepToUpdate = sleep) != 0)
            ctx.status(204)
        else
            ctx.status(404)
    }





}
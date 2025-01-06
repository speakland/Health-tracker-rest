package org.example.controllers

import io.javalin.http.Context
import org.example.domain.Statistic
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
        val statistics = statisticDao.getAll()
        if (statistics.size != 0) {
            ctx.status(200)
        }
        else{
            ctx.status(404)
        }
        ctx.json(statistics)
    }


    fun getStatisticsByStatisticId(ctx: Context) {
        val statistic = statisticDao.findByStatisticId(ctx.pathParam("statistic-id").toInt())
        if (statistic != null){
            ctx.json(statistic)
            ctx.status(200)
        }
        else{
            ctx.status(404)
        }
    }

    fun getStatisticsByUserId(ctx: Context) {
        val statistics = statisticDao.findByUserId(ctx.pathParam("user-id").toInt())
        if (statistics.isNotEmpty()) {
            ctx.json(statistics)
            ctx.status(200)
        }
        else{
            ctx.status(404)
        }
    }



    fun addStatistic(ctx: Context) {
        val statistic : Statistic = jsonToObject(ctx.body())
        val userId = userDao.findById(statistic.userId)
        if (userId != null) {
            val statisticId = statisticDao.save(statistic)
            statistic.id = statisticId
            ctx.json(statistic)
            ctx.status(201)
        }
        else{
            ctx.status(404)
        }
    }

    fun deleteStatisticByStatisticId(ctx: Context){
        if (statisticDao.deleteByStatisticId(ctx.pathParam("statistic-id").toInt()) != 0)
            ctx.status(204)
        else
            ctx.status(404)
    }

    fun deleteStatisticByUserId(ctx: Context){
        if (statisticDao.deleteByUserId(ctx.pathParam("user-id").toInt()) != 0)
            ctx.status(204)
        else
            ctx.status(404)
    }

    fun updateStatistic(ctx: Context){
        val statistic : Statistic = jsonToObject(ctx.body())
        if (statisticDao.updateByStatisticId (
                statisticId = ctx.pathParam("statistic-id").toInt(),
                statisticToUpdate = statistic) != 0)
            ctx.status(204)
        else
            ctx.status(404)
    }


}
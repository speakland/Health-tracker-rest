package org.example.controllers

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.javalin.http.Context
import org.example.domain.Activity
import org.example.domain.Nutrition
import org.example.domain.repository.NutritionDAO
import org.example.domain.repository.UserDAO
import org.example.utils.jsonToObject



object NutritionController {
    private val nutritionDao = NutritionDAO()
    private val userDao = UserDAO()
    //--------------------------------------------------------------
    // NutritionDAO specifics
    //-------------------------------------------------------------


    fun getAllNutritions(ctx: Context) {
        val nutritions = nutritionDao.getAll()
        if (nutritions.size != 0) {
            ctx.status(200)
        }
        else{
            ctx.status(404)
        }
        ctx.json(nutritions)
    }



    fun getNutritionsByNutritionId(ctx: Context) {
        val nutrition = nutritionDao.findByNutritionId(ctx.pathParam("nutrition-id").toInt())
        if (nutrition != null){
            ctx.json(nutrition)
            ctx.status(200)
        }
        else{
            ctx.status(404)
        }
    }







    fun getNutritionsByUserId(ctx: Context) {
        if (userDao.findById(ctx.pathParam("user-id").toInt()) != null) {
            val nutritions = nutritionDao.findByUserId(ctx.pathParam("user-id").toInt())
            if (nutritions.isNotEmpty()) {
                ctx.json(nutritions)
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


    fun addNutrition(ctx: Context) {
        val nutrition : Nutrition = jsonToObject(ctx.body())
        val userId = NutritionController.userDao.findById(nutrition.userId)
        if (userId != null) {
            val nutritionId = nutritionDao.save(nutrition)
            nutrition.id = nutritionId
            ctx.json(nutrition)
            ctx.status(201)
        }
        else{
            ctx.status(404)
        }
    }


    fun deleteNutritionByUserId(ctx: Context){
        if (nutritionDao.deleteByUserId(ctx.pathParam("user-id").toInt()) != 0)
            ctx.status(204)
        else
            ctx.status(404)
    }


    fun deleteNutritionByNutritionId(ctx: Context){
        if (nutritionDao.deleteByNutritionId(ctx.pathParam("nutrition-id").toInt()) != 0)
            ctx.status(204)
        else
            ctx.status(404)
    }


    fun updateNutrition(ctx: Context){
        val nutrition : Nutrition = jsonToObject(ctx.body())
        if (nutritionDao.updateByNutritionId(
                nutritionId = ctx.pathParam("nutrition-id").toInt(),
                nutritionToUpdate = nutrition) != 0)
            ctx.status(204)
        else
            ctx.status(404)
    }





}
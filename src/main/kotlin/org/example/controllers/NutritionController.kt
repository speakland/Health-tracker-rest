package org.example.controllers

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.javalin.http.Context
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
        //mapper handles the deserialization of Joda date into a String.
        val mapper = jacksonObjectMapper()
            .registerModule(JodaModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        ctx.json(mapper.writeValueAsString( nutritionDao.getAll() ))
    }

    fun getNutritionsByUserId(ctx: Context) {
        if (userDao.findById(ctx.pathParam("user-id").toInt()) != null) {
            val nutritions = nutritionDao.findByUserId(ctx.pathParam("user-id").toInt())
            if (nutritions.isNotEmpty()) {
                //mapper handles the deserialization of Joda date into a String.
                val mapper = jacksonObjectMapper()
                    .registerModule(JodaModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                ctx.json(mapper.writeValueAsString(nutritions))
            }
        }
    }

    fun addNutrition(ctx: Context) {
        //mapper handles the serialisation of Joda date into a String.
        val nutrition: Nutrition = jsonToObject(ctx.body())
        nutritionDao.save(nutrition)
        ctx.json(nutrition)
    }




}
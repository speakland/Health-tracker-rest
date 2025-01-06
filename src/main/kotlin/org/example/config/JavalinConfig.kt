package org.example.config

import io.javalin.Javalin
import io.javalin.json.JavalinJackson
import io.javalin.vue.VueComponent
import org.example.controllers.*
import org.example.utils.jsonObjectMapper
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.core.JsonProcessingException;


class JavalinConfig {


    fun startJavalinService(): Javalin {

        val app = Javalin.create{
            //added this jsonMapper for our integration tests - serialise objects to json
            it.jsonMapper(JavalinJackson(jsonObjectMapper()))
            it.staticFiles.enableWebjars()
            it.vue.vueInstanceNameInJs = "app" // only required for Vue 3, is defined in layout.html
        }.apply {
            exception(Exception::class.java) { e, _ -> e.printStackTrace() }
            error(404) { ctx -> ctx.json("404 : Not Found") }
        }.start(getRemoteAssignedPort())

        registerRoutes(app)
        return app
    }



    private fun registerRoutes(app: Javalin) {

        //users
        app.get("/api/users", UserController::getAllUsers)
        app.get("/api/users/{user-id}", UserController::getUserByUserId)
        app.get("/api/users/email/{email}", UserController::getUserByEmail)
        app.post("/api/users", UserController::addUser)
        app.patch("/api/users/{user-id}", UserController::updateUser)
        app.delete("/api/users/{user-id}", UserController::deleteUser)

        //activities
        app.get("/api/activities", ActivityController::getAllActivities)
        app.get("/api/users/{user-id}/activities", ActivityController::getActivitiesByUserId)
        app.get("/api/activities/{activity-id}", ActivityController::getActivitiesByActivityId )
        app.post("/api/activities", ActivityController::addActivity)
        app.patch("/api/activities/{activity-id}", ActivityController::updateActivity)
        app.delete("/api/users/{user-id}/activities", ActivityController::getActivitiesByUserId)
        app.delete("/api/activities/{activity-id}", ActivityController::deleteActivityByActivityId)

        //nutritions
        app.get("/api/nutritions", NutritionController::getAllNutritions)
        app.get("/api/users/{user-id}/nutritions", NutritionController::getNutritionsByUserId)
        app.get("/api/nutritions/{nutrition-id}", NutritionController::getNutritionsByNutritionId)
        app.post("/api/nutritions", NutritionController::addNutrition)
        app.patch("/api/nutritions/{nutrition-id}", NutritionController::updateNutrition)
        app.delete("/api/users/{user-id}/nutritions", NutritionController::getNutritionsByUserId)
        app.delete("/api/nutritions/{nutrition-id}", NutritionController::deleteNutritionByNutritionId)

        //sleeps
        app.get("/api/sleeps", SleepController::getAllSleeps)
        app.get("/api/users/{user-id}/sleeps", SleepController::getSleepsByUserId)
        app.get("/api/sleeps/{sleep-id}", SleepController::getSleepsBySleepId )
        app.post("/api/sleeps", SleepController::addSleep)
        app.patch("/api/sleeps/{sleep-id}", SleepController::updateSleep)
        app.delete("/api/users/{user-id}/sleeps", SleepController::getSleepsByUserId)
        app.delete("/api/sleeps/{sleep-id}", SleepController::deleteSleepBySleepId)

        //statistics
        app.get("/api/statistics", StatisticController::getAllStatistics)
        app.get("/api/users/{user-id}/statistics", StatisticController::getStatisticsByUserId)
        app.get("/api/statistics/{statistic-id}", StatisticController::getStatisticsByStatisticId)
        app.post("/api/statistics", StatisticController::addStatistic)
        app.patch("/api/statistics/{statistic-id}", StatisticController::updateStatistic)
        app.delete("/api/users/{user-id}/statistics", StatisticController::getStatisticsByUserId)
        app.delete("/api/statistics/{statistics-id}", StatisticController::deleteStatisticByStatisticId)



        // The @routeComponent that we added in layout.html earlier will be replaced
// by the String inside the VueComponent. This means a call to / will load
// the layout and display our <home-page> component.
        app.get("/", VueComponent("<home-page></home-page>"))
        app.get("/users", VueComponent("<user-overview></user-overview>"))
        app.get("/users/{user-id}", VueComponent("<user-profile></user-profile>"))
        app.get("/users/{user-id}/activities", VueComponent("<user-activity-overview></user-activity-overview>"))


    }



    private fun getRemoteAssignedPort(): Int {
        val remotePort = System.getenv("PORT")
        return if (remotePort != null) {
            Integer.parseInt(remotePort)
        } else 7001
    }

}
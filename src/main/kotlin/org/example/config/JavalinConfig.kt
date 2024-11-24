package org.example.config

import io.javalin.Javalin
import io.javalin.json.JavalinJackson
import org.example.controllers.ActivityController
import org.example.controllers.UserController
import org.example.utils.jsonObjectMapper

class JavalinConfig {

    fun startJavalinService(): Javalin {
        val app = Javalin.create {
            //add this jsonMapper to serialise objects to json
            it.jsonMapper(JavalinJackson(jsonObjectMapper()))
        }
            .apply{
                exception(Exception::class.java) { e, ctx -> e.printStackTrace() }
                error(404) { ctx -> ctx.json("404 - Not Found") }
            }
            .start(getRemoteAssignedPort())

        registerRoutes(app)
        return app
    }



    private fun registerRoutes(app: Javalin) {
        //users
        app.get("/api/users", UserController::getAllUsers)
        app.get("/api/users/{user-id}", UserController::getUserByUserId)
        app.delete("/api/users/{user-id}", UserController::deleteUser)
        app.patch("/api/users/{user-id}", UserController::updateUser)
        app.get("/api/users/email/{email}", UserController::getUserByEmail)
        app.post("/api/users", UserController::addUser)
        //activities
        app.get("/api/activities", ActivityController::getAllActivities)
        app.post("/api/activities", ActivityController::addActivity)
        app.get("/api/users/{user-id}/activities", ActivityController::getActivitiesByUserId)

        app.delete("/api/users/{user-id}/activities", ActivityController::deleteAllActivitiesByUser)
        app.delete("/api/activities/{activity-id}", ActivityController::deleteActivityByActivityId)
        app.patch("/api/activities/{activity-id}", ActivityController::updateActivityById)
        app.get("/api/activities/{activity-id}", ActivityController::getActivityById)

    }

    private fun getRemoteAssignedPort(): Int {
        val remotePort = System.getenv("PORT")
        return if (remotePort != null) {
            Integer.parseInt(remotePort)
        } else 7001
    }

}
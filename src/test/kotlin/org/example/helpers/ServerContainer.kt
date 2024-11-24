package org.example.helpers



import org.example.config.JavalinConfig

object ServerContainer {

    val instance by lazy {
        startServerContainer()
    }

    private fun startServerContainer() = JavalinConfig().startJavalinService()

}
package org.example

import org.example.config.DbConfig
import org.example.config.JavalinConfig

fun main() {

    DbConfig().getDbConnection()
    JavalinConfig().startJavalinService()

}
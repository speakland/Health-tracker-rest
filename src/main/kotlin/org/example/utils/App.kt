package org.example.utils

import org.example.config.DbConfig
import org.example.config.JavalinConfig


fun main() {

    DbConfig().getDbConnection()
    JavalinConfig().startJavalinService()

}
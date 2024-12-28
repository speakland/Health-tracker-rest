package org.example.config

import org.jetbrains.exposed.sql.Database
import org.postgresql.util.PSQLException

class DbConfig {

    private lateinit var dbConfig: Database

    fun getDbConnection(): Database {

        val PGHOST = "dpg-cto5rcogph6c73d513n0-a.frankfurt-postgres.render.com"
        val PGPORT = "5432"
        val PGUSER = "speakland"
        val PGPASSWORD = "G5EqkBC8OgT4YMZM6oVRVrRY5jZwql54"
        val PGDATABASE = "healthdb_smfe"

        //url format should be jdbc:postgresql://host:port/database
        val dbUrl = "jdbc:postgresql://$PGHOST:$PGPORT/$PGDATABASE"

        try {

            dbConfig = Database.connect(
                url = dbUrl, driver = "org.postgresql.Driver",
                user = PGUSER, password = PGPASSWORD
            )

        } catch (e: PSQLException) {
        }


        return dbConfig

    }
}





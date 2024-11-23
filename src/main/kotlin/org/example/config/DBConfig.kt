package org.example.config

import org.jetbrains.exposed.sql.Database
import org.postgresql.util.PSQLException

class DbConfig {

    private lateinit var dbConfig: Database

    fun getDbConnection(): Database {

        val PGHOST = "dpg-ct10crl6l47c73bamv2g-a.frankfurt-postgres.render.com"
        val PGPORT = "5432"
        val PGUSER = "speakland"
        val PGPASSWORD = "CAqWrfQOCMopUusI6mpo8je9gvVqIAL1"
        val PGDATABASE = "health_tracker_p7lo"

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





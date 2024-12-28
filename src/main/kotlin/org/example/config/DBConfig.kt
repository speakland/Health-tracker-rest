package org.example.config

import org.jetbrains.exposed.sql.Database
import org.postgresql.util.PSQLException

class DbConfig {

    private lateinit var dbConfig: Database

    fun getDbConnection(): Database {

        val PGHOST = "dpg-cto5bcrtq21c73crjhq0-a.frankfurt-postgres.render.com"
        val PGPORT = "5432"
        val PGUSER = "speakland"
        val PGPASSWORD = "fblRJ8zJyIlratKjJYsWtq9egh6RhOvH"
        val PGDATABASE = "healthtrackerdb_0ug8"

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





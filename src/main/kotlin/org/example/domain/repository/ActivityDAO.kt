package org.example.domain.repository

import org.example.domain.Activity
import org.example.domain.db.Activities
import org.example.utils.mapToActivity
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class ActivityDAO {

    //Get all the activities in the database regardless of user id
    fun getAll(): ArrayList<Activity> {
        val activitiesList: ArrayList<Activity> = arrayListOf()
        transaction {
            Activities.selectAll().map {
                activitiesList.add(mapToActivity(it)) }
        }
        return activitiesList
    }



    //Find all activities for a specific user id
    fun findByUserId(userId: Int): List<Activity>{
        return transaction {
            Activities
                .selectAll().where {Activities.userId eq userId}
                .map {mapToActivity(it)}
        }
    }

    //Save an activity to the database
    fun save(activity: Activity){
        transaction {
            Activities.insert {
                it[description] = activity.description
                it[duration] = activity.duration
                it[started] = activity.started
                it[calories] = activity.calories
                it[userId] = activity.userId
            }
        }
    }


//delete an activity by user id
    fun deleteAllByUserId (userId: Int){
        return transaction {
            Activities.deleteWhere{ Activities.userId eq userId }
        }
    }
//delete an activity by activity id
    fun delete (activity: Int){
        return transaction {
            Activities.deleteWhere{ Activities.id eq id }
        }
    }


    //Find a specific activity by activity id
    fun findByActivityId(id: Int): Activity?{
        return transaction {
            Activities
                .selectAll().where { Activities.id eq id}
                .map{mapToActivity(it)}
                .firstOrNull()
        }
    }

//Update an activity
    fun update(id: Int, activity: Activity){
        transaction {
            Activities.update ({ Activities.id eq id}) {
                it[description] = activity.description
                it[duration] = activity.duration
                it[started] = activity.started
                it[calories] = activity.calories
                it[userId] = activity.userId
            }
        }
    }







}
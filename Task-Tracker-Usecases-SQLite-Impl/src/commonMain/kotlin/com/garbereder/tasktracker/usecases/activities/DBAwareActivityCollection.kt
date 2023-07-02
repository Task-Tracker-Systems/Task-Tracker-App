package com.garbereder.tasktracker.usecases.activities

import com.garbereder.tasktracker.entities.Activity
import com.garbereder.tasktracker.entities.ActivityCollection
import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.usecases.sqlite.Database

class DBAwareActivityCollection(private val database: Database) :
    ActivityCollection {
    override fun add(activity: Activity) {
        database.transaction {
            val task = database.tasksQueries.findByName(activity.task.name).executeAsOne()
            database.activitiesQueries.insert(task.id, activity.durationInSeconds)
        }
    }

    override fun iterator(): Iterator<Activity> = database.activitiesQueries.selectAllWithTask().executeAsList()
        .map { (taskName, duration) -> Activity(duration, Task(taskName)) }.iterator()

    override fun remove(activity: Activity) {
        database.transaction {
            val task = database.tasksQueries.findByName(activity.task.name).executeAsOne()
            val act =
                database.activitiesQueries.findByTaskIdAndDuration(task.id, activity.durationInSeconds).executeAsOne()
            database.activitiesQueries.deleteById(act.id)
        }
    }
}

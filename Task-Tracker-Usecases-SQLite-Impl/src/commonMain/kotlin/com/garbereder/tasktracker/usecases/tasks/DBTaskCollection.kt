package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.entities.TaskCollection
import com.garbereder.tasktracker.usecases.sqlite.Database

class DBTaskCollection(private val database: Database) :
    TaskCollection {

    override fun add(task: Task) =
        database.transaction {
            database.tasksQueries.insert(task.name, task.totalDuration)
        }

    override fun iterator(): Iterator<Task> =
        database.tasksQueries.selectAll().executeAsList().map { t -> Task(t.name, t.total_duration) }.iterator()

    override fun remove(task: Task) =
        database.transaction {
            database.tasksQueries.deleteByName(task.name)
        }

    override fun replace(task: Task) =
        database.transaction {
            database.tasksQueries.setTotalDuration(task.totalDuration, task.name)
        }

    override fun size(): Int = database.tasksQueries.selectAll().executeAsList().count()
}

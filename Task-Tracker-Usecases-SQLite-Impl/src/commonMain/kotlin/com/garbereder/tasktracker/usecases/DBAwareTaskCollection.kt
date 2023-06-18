package com.garbereder.tasktracker.usecases

import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.entities.TaskCollection
import com.garbereder.tasktracker.usecases.sqlite.Database

class DBAwareTaskCollection(private val baseCollection: TaskCollection, private val database: Database):
    TaskCollection {
    override fun add(task: Task) {
        database.transaction {
            database.tasksQueries.insert(task.id, task.name)
            baseCollection.add(task)
        }
    }

    override fun iterator(): Iterator<Task> = baseCollection.iterator()

    override fun remove(task: Task) {
        database.transaction {
            database.tasksQueries.deleteById(task.id)
            baseCollection.remove(task)
        }
    }
    override fun size(): Int = baseCollection.size()
}
package com.garbereder.tasktracker.usecases

import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.entities.TaskCollection
import com.garbereder.tasktracker.entities.TaskCollectionImpl
import com.garbereder.tasktracker.usecases.sqlite.Database
import com.garbereder.tasktracker.usecases.sqlite.DriverFactory
import com.garbereder.tasktracker.usecases.tasks.TaskCollectionReader

class DBTaskCollectionReader internal constructor(private val driverFactory: DriverFactory) : TaskCollectionReader {
    override fun read(): TaskCollection {
        val database = Database(driverFactory.createDriver())
        val tasks = database.tasksQueries.selectAll()
        val taskList = tasks.executeAsList()
        val collection = TaskCollectionImpl()
        taskList.forEach { t -> collection.add(Task(t.task_id, t.name)) }
        return DBAwareTaskCollection(collection, database)
    }
}


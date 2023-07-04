package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.TaskCollection
import com.garbereder.tasktracker.usecases.sqlite.Database
import com.garbereder.tasktracker.usecases.sqlite.DriverFactory

class DBTaskCollectionReader internal constructor(private val driverFactory: DriverFactory) : TaskCollectionReader {
    override fun read(): TaskCollection {
        val database = Database(driverFactory.createDriver())
        return DBAwareTaskCollection(database)
    }
}

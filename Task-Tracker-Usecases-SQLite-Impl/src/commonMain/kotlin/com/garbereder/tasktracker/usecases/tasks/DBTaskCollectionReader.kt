package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.TaskCollection
import com.garbereder.tasktracker.usecases.sqlite.Database

class DBTaskCollectionReader internal constructor(private val driverFactory: DBDriverFactory) : TaskCollectionReader {
    override fun read(): TaskCollection = DBTaskCollection(Database(driverFactory.createDriver()))
}

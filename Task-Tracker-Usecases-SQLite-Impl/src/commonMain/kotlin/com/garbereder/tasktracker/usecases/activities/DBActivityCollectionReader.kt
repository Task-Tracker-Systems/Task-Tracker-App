package com.garbereder.tasktracker.usecases.activities

import com.garbereder.tasktracker.entities.ActivityCollection
import com.garbereder.tasktracker.usecases.sqlite.Database
import com.garbereder.tasktracker.usecases.sqlite.DriverFactory

class DBActivityCollectionReader internal constructor(private val driverFactory: DriverFactory) : ActivityCollectionReader {
    override fun read(): ActivityCollection {
        val database = Database(driverFactory.createDriver())
        return DBAwareActivityCollection(database)
    }
}

package app.tasktrackersystems.tasktracker.usecases.tasks

import app.tasktrackersystems.tasktracker.entities.TaskCollection
import app.tasktrackersystems.tasktracker.usecases.sqlite.Database

class DBTaskCollectionReader internal constructor(private val driverFactory: DBDriverFactory) : TaskCollectionReader {
    override fun read(): TaskCollection = DBTaskCollection(Database(driverFactory.createDriver()))
}

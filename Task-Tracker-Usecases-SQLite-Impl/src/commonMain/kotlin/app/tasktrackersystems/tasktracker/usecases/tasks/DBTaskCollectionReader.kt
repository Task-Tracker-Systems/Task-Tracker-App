package app.tasktrackersystems.tasktracker.usecases.tasks

import app.tasktrackersystems.tasktracker.entities.TaskCollection
import app.tasktrackersystems.tasktracker.usecases.sqlite.Database

class DBTaskCollectionReader constructor(private val database: Database) : TaskCollectionReader {
    override fun read(): TaskCollection = DBTaskCollection(database)
}

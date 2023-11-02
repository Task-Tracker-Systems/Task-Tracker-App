package app.tasktrackersystems.tasktracker.usecases.tasks

import app.tasktrackersystems.tasktracker.entities.TaskCollection

interface TaskCollectionReader {
    fun read(): TaskCollection
}

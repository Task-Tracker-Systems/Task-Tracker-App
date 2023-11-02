package app.tasktrackersystems.tasktracker.usecases.tasks

import app.tasktrackersystems.tasktracker.entities.Task
import app.tasktrackersystems.tasktracker.entities.TaskCollection
import app.tasktrackersystems.tasktracker.usecases.UseCase

class ListTasks(private val collection: TaskCollection) : UseCase<Iterator<Task>> {
    override fun invoke(): Iterator<Task> = collection.iterator()
}

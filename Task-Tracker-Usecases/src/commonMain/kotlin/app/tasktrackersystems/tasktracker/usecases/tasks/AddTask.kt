package app.tasktrackersystems.tasktracker.usecases.tasks

import app.tasktrackersystems.tasktracker.entities.Task
import app.tasktrackersystems.tasktracker.entities.TaskCollection
import app.tasktrackersystems.tasktracker.usecases.UseCase

class AddTask constructor(private val collection: TaskCollection, private val taskName: String) : UseCase<Task> {
    override fun invoke(): Task {
        val task = Task(taskName, 0L)
        collection.add(task)
        return task
    }
}

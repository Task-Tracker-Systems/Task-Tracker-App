package app.tasktrackersystems.tasktracker.usecases.tasks

import app.tasktrackersystems.tasktracker.entities.Task
import app.tasktrackersystems.tasktracker.entities.TaskCollection
import app.tasktrackersystems.tasktracker.usecases.UseCase

class RenameTask constructor(private val collection: TaskCollection, private val task: Task, private val taskNameNew: String) : UseCase<Task> {
    override fun invoke(): Task {
        val task2 = task.copy(name = taskNameNew)
        collection.replace(task, task2)
        return task2
    }
}

package app.tasktrackersystems.tasktracker.usecases.tasks

import app.tasktrackersystems.tasktracker.entities.Task
import app.tasktrackersystems.tasktracker.entities.TaskCollection
import app.tasktrackersystems.tasktracker.usecases.UseCase

class RemoveTask(private val collection: TaskCollection, private val task: Task) : UseCase<Unit> {
    override fun invoke() = collection.remove(task)
}

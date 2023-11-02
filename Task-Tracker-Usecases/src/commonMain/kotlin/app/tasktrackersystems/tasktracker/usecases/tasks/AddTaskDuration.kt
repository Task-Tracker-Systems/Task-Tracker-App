package app.tasktrackersystems.tasktracker.usecases.tasks

import app.tasktrackersystems.tasktracker.entities.Task
import app.tasktrackersystems.tasktracker.entities.TaskCollection
import app.tasktrackersystems.tasktracker.usecases.UseCase

class AddTaskDuration(private val collection: TaskCollection, private val task: Task, private val duration: Long) :
    UseCase<Unit> {
    override fun invoke() = collection.replace(task.copy(totalDuration = task.totalDuration + duration))
}

package app.tasktrackersystems.tasktracker.usecases.tasks

import app.tasktrackersystems.tasktracker.entities.Task
import app.tasktrackersystems.tasktracker.entities.TaskCollection
import app.tasktrackersystems.tasktracker.usecases.UseCase

class SetTaskDuration(private val collection: TaskCollection, private var task: Task, private val duration: Long) :
    UseCase<Task> {
    override fun invoke(): Task {
        val task2 = task.copy(totalDuration = duration)
        collection.replace(task, task2)
        return task2
    }
}

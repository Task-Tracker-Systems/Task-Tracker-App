package app.tasktrackersystems.tasktracker.usecases

import app.tasktrackersystems.tasktracker.entities.Task
import app.tasktrackersystems.tasktracker.entities.TaskCollection
import app.tasktrackersystems.tasktracker.usecases.tasks.AddTask
import app.tasktrackersystems.tasktracker.usecases.tasks.AddTaskDuration
import app.tasktrackersystems.tasktracker.usecases.tasks.ListTasks
import app.tasktrackersystems.tasktracker.usecases.tasks.RemoveTask
import app.tasktrackersystems.tasktracker.usecases.tasks.TaskCollectionReader

class UseCases(
    private val taskCollection: TaskCollection
) {

    companion object {
        fun createUseCasesFromReaders(
            taskReader: TaskCollectionReader
        ): UseCases = UseCases(taskReader.read())
    }

    // tasks
    fun createAddTask(taskName: String): UseCase<Unit> = AddTask(taskCollection, taskName)
    fun createListTasks(): UseCase<Iterator<Task>> = ListTasks(taskCollection)
    fun createAddTaskDuration(task: Task, duration: Long): UseCase<Unit> = AddTaskDuration(taskCollection, task, duration)
    fun createRemoveTask(task: Task): UseCase<Unit> = RemoveTask(taskCollection, task)
}

package com.garbereder.tasktracker.usecases

import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.entities.TaskCollection
import com.garbereder.tasktracker.usecases.tasks.AddTask
import com.garbereder.tasktracker.usecases.tasks.AddTaskDuration
import com.garbereder.tasktracker.usecases.tasks.ListTasks
import com.garbereder.tasktracker.usecases.tasks.RemoveTask
import com.garbereder.tasktracker.usecases.tasks.TaskCollectionReader

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
    fun createRemoveTasks(task: Task): UseCase<Unit> = RemoveTask(taskCollection, task)
}

package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.entities.TaskCollection
import com.garbereder.tasktracker.usecases.UseCase

class TaskUseCases(private val collection: TaskCollection) {

    companion object {
        fun createTaskUseCasesFromLoadTasks(reader: TaskCollectionReader): UseCase<TaskUseCases> = LoadTasks(reader)
    }

    fun createAddTask(taskName: String): UseCase<Unit> = AddTask(collection, taskName)
    fun createListTasks(): UseCase<Iterator<Task>> = ListTasks(collection)
    fun createRemoveTasks(task: Task): UseCase<Unit> = RemoveTask(collection, task)
}

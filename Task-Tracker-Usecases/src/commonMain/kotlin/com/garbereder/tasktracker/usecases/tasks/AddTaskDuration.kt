package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.entities.TaskCollection
import com.garbereder.tasktracker.usecases.UseCase

class AddTaskDuration(private val collection: TaskCollection, private val task: Task, private val duration: Long) : UseCase<Unit> {
    override fun invoke() = collection.replace(task.copy(totalDuration = task.totalDuration + duration))
}

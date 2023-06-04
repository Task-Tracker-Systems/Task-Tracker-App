package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.entities.TaskCollection
import com.garbereder.tasktracker.usecases.UseCase

class RemoveTask(private val collection: TaskCollection, private val task: Task): UseCase {
    override fun invoke() = collection.remove(task)
}
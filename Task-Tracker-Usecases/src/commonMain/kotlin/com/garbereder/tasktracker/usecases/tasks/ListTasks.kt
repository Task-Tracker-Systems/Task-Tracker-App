package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.entities.TaskCollection
import com.garbereder.tasktracker.usecases.UseCase

class ListTasks(private val collection: TaskCollection): UseCase<Iterator<Task>> {
    override fun invoke(): Iterator<Task> = collection.iterator()
}
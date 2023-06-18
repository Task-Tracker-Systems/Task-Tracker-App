package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.entities.TaskCollection
import com.garbereder.tasktracker.usecases.UseCase

class AddTask constructor(private val collection: TaskCollection, private val taskName: String) : UseCase<Unit> {
    override fun invoke() = collection.add(Task("${collection.size() + 1}", taskName))
}

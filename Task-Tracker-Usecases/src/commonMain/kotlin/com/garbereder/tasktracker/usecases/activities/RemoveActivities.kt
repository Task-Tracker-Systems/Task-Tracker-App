package com.garbereder.tasktracker.usecases.activities

import com.garbereder.tasktracker.entities.Activity
import com.garbereder.tasktracker.entities.ActivityCollection
import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.usecases.UseCase

class RemoveActivities(private val collection: ActivityCollection, private val task: Task) : UseCase<Unit> {
    override fun invoke() {
        val activities = collection.filter { c -> c.task == task }
        activities.forEach { a -> collection.remove(a) }
    }
}

package com.garbereder.tasktracker.usecases.activities

import com.garbereder.tasktracker.entities.Activity
import com.garbereder.tasktracker.entities.ActivityCollection
import com.garbereder.tasktracker.usecases.UseCase

class AddActivity(private val collection: ActivityCollection, private val activity: Activity) : UseCase<Unit> {
    override fun invoke(): Unit = collection.add(activity)
}

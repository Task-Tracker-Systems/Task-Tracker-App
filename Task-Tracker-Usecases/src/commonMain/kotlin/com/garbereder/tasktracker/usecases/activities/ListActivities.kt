package com.garbereder.tasktracker.usecases.activities

import com.garbereder.tasktracker.entities.Activity
import com.garbereder.tasktracker.entities.ActivityCollection
import com.garbereder.tasktracker.usecases.UseCase

class ListActivities(private val collection: ActivityCollection) : UseCase<Iterator<Activity>> {
    override fun invoke(): Iterator<Activity> = collection.iterator()
}

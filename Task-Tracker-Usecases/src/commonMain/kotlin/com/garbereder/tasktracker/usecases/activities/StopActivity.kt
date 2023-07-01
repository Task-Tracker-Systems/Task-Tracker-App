package com.garbereder.tasktracker.usecases.activities

import com.garbereder.tasktracker.entities.Activity
import com.garbereder.tasktracker.entities.ActivityCollection
import com.garbereder.tasktracker.usecases.UseCase

class StopActivity(private val collection: ActivityCollection, private val activity: Activity, private val durationInSeconds: Long) : UseCase<Activity> {
    override fun invoke(): Activity {
        val act = activity.copy(durationInSeconds = durationInSeconds)
        collection.add(act)
        return act
    }

}

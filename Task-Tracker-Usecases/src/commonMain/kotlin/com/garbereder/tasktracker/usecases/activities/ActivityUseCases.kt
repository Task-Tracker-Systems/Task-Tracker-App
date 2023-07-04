package com.garbereder.tasktracker.usecases.activities

import com.garbereder.tasktracker.entities.Activity
import com.garbereder.tasktracker.entities.ActivityCollection
import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.usecases.UseCase

class ActivityUseCases(private val collection: ActivityCollection) {

    companion object {
        fun createActivityUseCasesFromLoadActivities(reader: ActivityCollectionReader): UseCase<ActivityUseCases> = LoadActivities(reader)
    }

    fun createStartActivity(task: Task): UseCase<Activity> = StartActivity(task)
    fun createStopActivity(activity: Activity, duration: Long): UseCase<Activity> = StopActivity(collection, activity, duration)
    fun createListActivity(): UseCase<Iterator<Activity>> = ListActivities(collection)
    fun createRemoveActivities(task: Task): UseCase<Unit> = RemoveActivities(collection, task)
}

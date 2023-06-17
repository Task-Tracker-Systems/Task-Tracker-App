package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.ActivityCollection
import com.garbereder.tasktracker.usecases.UseCase

class LoadActivities(private val reader: ActivityCollectionReader): UseCase<ActivityCollection> {
    override fun invoke(): ActivityCollection = reader.read()

}

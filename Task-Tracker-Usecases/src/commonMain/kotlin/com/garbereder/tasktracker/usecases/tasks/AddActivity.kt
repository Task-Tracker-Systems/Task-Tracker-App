package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.Activity
import com.garbereder.tasktracker.entities.ActivityCollection
import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.usecases.UseCase
import kotlinx.datetime.Clock

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class AddActivity(private val collection: ActivityCollection, private val activity: Activity): UseCase<Unit> {
    override fun invoke(): Unit = collection.add(activity)
}
package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.Activity
import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.usecases.UseCase
import kotlinx.datetime.Clock

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class StopActivity(private val activity: Activity): UseCase<Activity> {
    override fun invoke(): Activity = activity.copy(end = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()))
}
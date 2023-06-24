package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.Activity
import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.usecases.UseCase
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class StartActivity(private val task: Task) : UseCase<Activity> {
    override fun invoke(): Activity = Activity("1", Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()), null, task)
}

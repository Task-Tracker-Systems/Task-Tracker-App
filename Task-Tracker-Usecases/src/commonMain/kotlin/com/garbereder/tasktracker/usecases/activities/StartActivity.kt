package com.garbereder.tasktracker.usecases.activities

import com.garbereder.tasktracker.entities.Activity
import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.usecases.UseCase

class StartActivity(private val task: Task) : UseCase<Activity> {
    override fun invoke(): Activity = Activity(0, task)
}

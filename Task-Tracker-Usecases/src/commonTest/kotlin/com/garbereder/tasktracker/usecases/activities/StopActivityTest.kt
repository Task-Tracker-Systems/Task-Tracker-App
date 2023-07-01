package com.garbereder.tasktracker.usecases.activities

import com.garbereder.tasktracker.entities.Activity
import com.garbereder.tasktracker.entities.Task
import kotlin.test.Test
import kotlin.test.assertEquals

class StopActivityTest {

    @Test
    fun invoke_noInput_setStart() {
        val task = Task("TaskName")
        val activity = Activity(0, task)
        val activity2 = StopActivity(activity, 50).invoke()

        assertEquals(0, activity.durationInSeconds)

        assertEquals(50, activity2.durationInSeconds)
        assertEquals(task, activity2.task)
    }
}

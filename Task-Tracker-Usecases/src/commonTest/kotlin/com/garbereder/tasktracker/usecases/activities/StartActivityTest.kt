package com.garbereder.tasktracker.usecases.activities

import com.garbereder.tasktracker.entities.Task
import kotlin.test.Test
import kotlin.test.assertEquals

class StartActivityTest {

    @Test
    fun invoke_noInput_setStart() {
        val task = Task("TaskName")
        val activity = StartActivity(task).invoke()

        assertEquals(0, activity.durationInSeconds)
        assertEquals(task, activity.task)
    }
}

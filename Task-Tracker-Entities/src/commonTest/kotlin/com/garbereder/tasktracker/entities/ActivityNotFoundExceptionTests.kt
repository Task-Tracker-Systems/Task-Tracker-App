package com.garbereder.tasktracker.entities

import kotlin.test.Test
import kotlin.test.assertEquals

class ActivityNotFoundExceptionTests {

    @Test
    fun testMessage() {
        val task = Task("TaskName")
        val duration = 60L
        val activity = Activity(duration, task)
        val ex = ActivityNotFoundException(activity)
        assertEquals("Could not find activity: Activity(durationInSeconds=$duration, task=$task)", ex.message)
    }
}

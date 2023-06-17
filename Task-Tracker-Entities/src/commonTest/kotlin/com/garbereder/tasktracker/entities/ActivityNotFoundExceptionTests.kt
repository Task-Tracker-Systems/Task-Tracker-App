package com.garbereder.tasktracker.entities

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class ActivityNotFoundExceptionTests {

    @Test
    fun testMessage() {
        val task = Task("TaskId", "TaskName")
        val start = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val end = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val activity = Activity("1", start, end, task )
        val ex = ActivityNotFoundException(activity)
        assertEquals("Could not find activity: Activity(id=1, start=${start}, end=${end}, task=${task})", ex.message)
    }

}
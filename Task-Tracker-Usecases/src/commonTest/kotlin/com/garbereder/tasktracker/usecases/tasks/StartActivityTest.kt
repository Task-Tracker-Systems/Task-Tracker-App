package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.Task
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.test.Test
import kotlin.test.assertTrue

class StartActivityTest {

    @Test
    fun invoke_noInput_setStart() {
        val task = Task("1", "TaskName")
        val before = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val activity = StartActivity(task).invoke()
        val after = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

        assertTrue(activity.id == "1")
        assertTrue(activity.end === null)
        assertTrue(activity.start >= before)
        assertTrue(activity.start <= after)
    }
}

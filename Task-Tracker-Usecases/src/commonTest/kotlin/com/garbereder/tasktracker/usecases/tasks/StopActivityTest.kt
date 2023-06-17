package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.Activity
import com.garbereder.tasktracker.entities.Task
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.test.Test
import kotlin.test.assertTrue

class StopActivityTest {

    @Test
    fun invoke_noInput_setStart() {
        val task = Task("1","TaskName")
        val before = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val activity = Activity("1", before, null, task)
        val activity2 = StopActivity(activity).invoke()
        val after = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

        assertTrue( activity.end === null )

        assertTrue( activity2.id == "1" )
        assertTrue( activity2.start !== activity2.end )
        assertTrue( activity2.end!! >= before )
        assertTrue( activity2.end!! <= after )
    }
}
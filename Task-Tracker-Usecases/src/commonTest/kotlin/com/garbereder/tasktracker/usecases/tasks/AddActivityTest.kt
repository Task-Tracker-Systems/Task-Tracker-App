package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.Activity
import com.garbereder.tasktracker.entities.ActivityCollection
import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.entities.TaskCollection
import io.mockative.*
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.test.Test
import kotlin.test.assertTrue

class AddActivityTest {

    @Mock
    val collection = mock(classOf<ActivityCollection>())

    @Test
    fun invoke_activity_callAdd() {
        val task = Task("1","TaskName")
        val time = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val activity = Activity("1", time, null, task)

        given(collection).invocation { add(activity) }
            .thenDoNothing()

        AddActivity(collection, activity).invoke()

        verify(collection).invocation { add(activity) }
            .wasInvoked(exactly = once)
    }
}
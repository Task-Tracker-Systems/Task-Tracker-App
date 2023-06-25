package com.garbereder.tasktracker.usecases.activities

import com.garbereder.tasktracker.entities.Activity
import com.garbereder.tasktracker.entities.ActivityCollection
import com.garbereder.tasktracker.entities.Task
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import io.mockative.once
import io.mockative.thenDoNothing
import io.mockative.verify
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.test.Test

class AddActivityTest {

    @Mock
    val collection = mock(classOf<ActivityCollection>())

    @Test
    fun invoke_activity_callAdd() {
        val task = Task("1", "TaskName")
        val time = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val activity = Activity("1", time, null, task)

        given(collection).invocation { add(activity) }
            .thenDoNothing()

        AddActivity(collection, activity).invoke()

        verify(collection).invocation { add(activity) }
            .wasInvoked(exactly = once)
    }
}

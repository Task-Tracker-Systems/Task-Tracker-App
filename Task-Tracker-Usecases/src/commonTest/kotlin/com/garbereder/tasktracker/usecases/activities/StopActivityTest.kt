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
import kotlin.test.Test
import kotlin.test.assertEquals

class StopActivityTest {

    @Mock
    val collection = mock(classOf<ActivityCollection>())

    @Test
    fun invoke_noInput_setStart() {
        val task = Task("TaskName")
        val activity = Activity(0, task)

        given(collection)
            .invocation { add(activity.copy(durationInSeconds = 50L)) }
            .thenDoNothing()

        val activity2 = StopActivity(collection, activity, 50L).invoke()

        assertEquals(0, activity.durationInSeconds)
        assertEquals(50, activity2.durationInSeconds)
        assertEquals(task, activity2.task)
        verify(collection)
            .invocation { add(activity2) }
            .wasInvoked(exactly = once)
    }
}

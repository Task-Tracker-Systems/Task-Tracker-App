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

class AddActivityTest {

    @Mock
    val collection = mock(classOf<ActivityCollection>())

    @Test
    fun invoke_activity_callAdd() {
        val task = Task("TaskName")
        val activity = Activity(0, task)

        given(collection).invocation { add(activity) }
            .thenDoNothing()

        AddActivity(collection, activity).invoke()

        verify(collection).invocation { add(activity) }
            .wasInvoked(exactly = once)
    }
}

package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.ActivityCollection
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import io.mockative.once
import io.mockative.verify
import kotlin.test.Test
import kotlin.test.assertEquals

class LoadActivitiesTests {
    @Mock
    val reader = mock(classOf<ActivityCollectionReader>())

    @Mock
    val collection = mock(classOf<ActivityCollection>())

    @Test
    fun invoke_noInput_callsThrough() {
        given(reader).invocation { read() }
            .then { collection }

        val col = LoadActivities(reader).invoke()
        assertEquals(collection, col)

        verify(reader).invocation { read() }
            .wasInvoked(exactly = once)
    }
}

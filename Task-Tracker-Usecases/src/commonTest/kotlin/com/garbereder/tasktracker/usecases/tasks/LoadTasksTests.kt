package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.TaskCollection
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import io.mockative.once
import io.mockative.verify
import kotlin.test.Test
import kotlin.test.assertEquals

class LoadTasksTests {
    @Mock
    val reader = mock(classOf<TaskCollectionReader>())

    @Mock
    val collection = mock(classOf<TaskCollection>())

    @Test
    fun invoke_noInput_callsThrough() {
        given(reader).invocation { read() }
            .then { collection }

        val col = LoadTasks(reader).invoke()
        assertEquals(collection, col)

        verify(reader).invocation { read() }
            .wasInvoked(exactly = once)
    }
}

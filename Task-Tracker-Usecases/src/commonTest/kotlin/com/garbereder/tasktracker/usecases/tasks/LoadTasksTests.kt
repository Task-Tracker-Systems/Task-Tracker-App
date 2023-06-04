package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.entities.TaskCollection
import com.garbereder.tasktracker.usecases.UseCase
import io.mockative.*
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class LoadTasksTests {
    @Mock
    val reader = mock(classOf<TaskCollectionReader>())
    @Mock
    val collection = mock(classOf<TaskCollection>())
    @Test
    fun invoke_noInput_callsThrough() {
        given(reader).invocation { read() }
            .then { collection }

        LoadTasks(reader).invoke()

        verify(reader).invocation { read() }
            .wasInvoked(exactly = once)
    }

}
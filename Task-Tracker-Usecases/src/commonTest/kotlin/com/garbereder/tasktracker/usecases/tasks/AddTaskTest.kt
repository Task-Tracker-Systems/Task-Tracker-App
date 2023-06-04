package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.entities.TaskCollection
import io.mockative.*
import kotlin.test.*

class AddTaskTests {
    @Mock
    val collection = mock(classOf<TaskCollection>())

    @Test
    fun invoke_noInput_callsThrough() {
        val task = Task("1","TaskName")
        given(collection).invocation { add(task) }
            .thenDoNothing()
        given(collection).invocation { size() }
            .then { 0 }

        AddTask(collection, "TaskName").invoke()

        verify(collection).invocation { size() }
            .wasInvoked(exactly = once)
        verify(collection).invocation { add(task) }
            .wasInvoked(exactly = once)
    }

}
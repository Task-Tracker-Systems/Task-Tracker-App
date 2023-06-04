package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.entities.TaskCollection
import com.garbereder.tasktracker.entities.TaskNotFoundException
import io.mockative.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RemoveTaskTest {
    @Mock
    val collection = mock(classOf<TaskCollection>())
    @Test
    fun invoke_noInput_callsThrough() {
        val task = Task("TaskId", "TaskName")
        given(collection).invocation { remove(task) }
            .thenDoNothing()
        RemoveTask(collection, task).invoke()

        verify(collection).invocation { remove(task) }
            .wasInvoked(exactly = once)
    }
}
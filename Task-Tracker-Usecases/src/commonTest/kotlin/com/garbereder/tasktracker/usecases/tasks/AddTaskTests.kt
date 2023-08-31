package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.entities.TaskCollection
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import io.mockative.once
import io.mockative.thenDoNothing
import io.mockative.verify
import kotlin.test.Test

class AddTaskTests {
    @Mock
    val collection = mock(classOf<TaskCollection>())

    @Test
    fun addOneEntry() {
        val task = Task("TaskName", 0L)
        given(collection).invocation { add(task) }
            .thenDoNothing()

        AddTask(collection, "TaskName").invoke()

        verify(collection).invocation { add(task) }
            .wasInvoked(exactly = once)
    }
}

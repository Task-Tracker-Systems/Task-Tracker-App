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

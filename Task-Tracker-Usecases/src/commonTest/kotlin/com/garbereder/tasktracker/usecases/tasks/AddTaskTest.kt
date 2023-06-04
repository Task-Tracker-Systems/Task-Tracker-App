package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.entities.TaskCollection
import io.mockative.*
import kotlin.test.*

class AddTaskTes {
    @Mock
    val collection = mock(classOf<TaskCollection>())

    @Test
    fun invoke_noInput_callsThrough() {
        val task = Task("TaskId", "TaskName")
        given(collection).invocation { add(task) }
            .thenDoNothing()

        AddTask(collection, task).invoke()

        verify(collection).invocation { add(task) }
            .wasInvoked(exactly = once)
    }

}
package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.entities.TaskCollection
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import io.mockative.once
import io.mockative.verify
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ListTasksTests {
    @Mock
    val collection = mock(classOf<TaskCollection>())

    @Test
    fun invoke_noInput_callsThrough() {
        val tasks = listOf(
            Task("TaskName"),
            Task("TaskName2")
        )
        given(collection).invocation { iterator() }
            .then { tasks.iterator() }

        val it = ListTasks(collection).invoke()

        verify(collection).invocation { iterator() }
            .wasInvoked(exactly = once)

        assertTrue(it.hasNext())
        assertEquals(tasks[0], it.next())
        assertEquals(tasks[1], it.next())
        assertFalse(it.hasNext())
    }
}

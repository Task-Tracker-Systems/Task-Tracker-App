package com.garbereder.tasktracker.entities

import kotlin.test.Test
import kotlin.test.assertEquals

class TaskNotFoundExceptionTests {

    @Test
    fun testMessage() {
        val task = Task("TaskName")
        val ex = TaskNotFoundException(task)
        assertEquals("Could not find task: Task(name=TaskName)", ex.message)
    }
}

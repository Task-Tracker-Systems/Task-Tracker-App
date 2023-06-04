package com.garbereder.tasktracker.entities

import kotlin.test.Test
import kotlin.test.assertEquals

class TaskNotFoundExceptionTests {

    @Test
    fun testMessage() {
        val task = Task("TaskId", "TaskName")
        val ex = TaskNotFoundException(task)
        assertEquals("Could not find task: Task(id=TaskId, name=TaskName)", ex.message)
    }

}
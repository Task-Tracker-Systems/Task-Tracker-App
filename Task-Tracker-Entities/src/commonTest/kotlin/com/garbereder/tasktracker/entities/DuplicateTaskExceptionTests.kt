package com.garbereder.tasktracker.entities

import kotlin.test.Test
import kotlin.test.assertEquals

class DuplicateTaskExceptionTests {

    @Test
    fun testMessage() {
        val task = Task("TaskId", "TaskName")
        val ex = DuplicateTaskException(task)
        assertEquals("Could not add duplicate task: Task(id=TaskId, name=TaskName)", ex.message)
    }
}

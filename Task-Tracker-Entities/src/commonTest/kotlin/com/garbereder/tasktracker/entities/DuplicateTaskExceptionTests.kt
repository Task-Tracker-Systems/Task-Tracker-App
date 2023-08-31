package com.garbereder.tasktracker.entities

import kotlin.test.Test
import kotlin.test.assertEquals

class DuplicateTaskExceptionTests {

    @Test
    fun testMessage() {
        val task = Task("TaskName", 0L)
        val ex = DuplicateTaskException(task)
        assertEquals("Could not add duplicate task: Task(name=TaskName, totalDuration=0)", ex.message)
    }
}

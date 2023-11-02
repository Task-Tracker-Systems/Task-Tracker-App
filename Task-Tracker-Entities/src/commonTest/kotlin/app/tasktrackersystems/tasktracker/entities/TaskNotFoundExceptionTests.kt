package app.tasktrackersystems.tasktracker.entities

import kotlin.test.Test
import kotlin.test.assertEquals

class TaskNotFoundExceptionTests {

    @Test
    fun testMessage() {
        val task = Task("TaskName", 0L)
        val ex = TaskNotFoundException(task)
        assertEquals("Could not find task: Task(name=TaskName, totalDuration=0)", ex.message)
    }
}

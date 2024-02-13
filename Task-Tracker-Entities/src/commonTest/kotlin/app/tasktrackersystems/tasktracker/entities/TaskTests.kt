package app.tasktrackersystems.tasktracker.entities

import kotlin.test.Test
import kotlin.test.assertEquals

class TaskTests {

    @Test
    fun testComparison() {
        val task = Task("TaskName", 0L)
        val task2 = Task("TaskName", 0L)
        assertEquals(task, task2)
    }
}

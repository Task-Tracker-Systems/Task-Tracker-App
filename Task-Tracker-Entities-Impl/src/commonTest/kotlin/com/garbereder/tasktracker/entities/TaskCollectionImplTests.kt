package com.garbereder.tasktracker.entities

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ListTaskCollectionImpl {

    @Test
    fun add_once_oneEntry() {
        val col = TaskCollectionImpl()
        assertEquals(0, col.size())

        val task = Task("TaskId", "TaskName")
        col.add(task)

        assertEquals(1, col.size())
        assertEquals(task, col.iterator().next())
    }

    @Test
    fun add_twice_twoEntries() {
        val col = TaskCollectionImpl()
        assertEquals(0, col.size())

        val task = Task("TaskId", "TaskName")
        val task2 = Task("TaskId2", "TaskName2")
        col.add(task)
        col.add(task2)

        assertEquals(2, col.size())
        val it = col.iterator()
        assertEquals(task, it.next())
        assertEquals(task2, it.next())
    }

    @Test
    fun add_twice_exception() {
        val col = TaskCollectionImpl()
        assertEquals(0, col.size())

        val task = Task("TaskId", "TaskName")
        col.add(task)

        assertFailsWith<DuplicateTaskException> {
            col.add(task)
        }

        assertEquals(1, col.size())
        assertEquals(task, col.iterator().next())
    }

    @Test
    fun remove_existingEntry_emptyList() {
        val col = TaskCollectionImpl()
        val task = Task("TaskId", "TaskName")

        col.add(task)
        assertEquals(1, col.size())
        col.remove(task)
        assertEquals(0, col.size())
    }

    @Test
    fun remove_existingEntry_removedEntry() {
        val col = TaskCollectionImpl()
        val task = Task("TaskId", "TaskName")
        val task2 = Task("TaskId2", "TaskName")

        col.add(task)
        col.add(task2)
        assertEquals(2, col.size())
        col.remove(task)
        assertEquals(1, col.size())
        assertEquals(task2, col.iterator().next())
    }

    @Test
    fun remove_empty_exception() {
        val col = TaskCollectionImpl()
        val task = Task("TaskId", "TaskName")

        assertFailsWith<TaskNotFoundException> {
            col.remove(task)
        }
    }

    @Test
    fun remove_nonEmptyNotExistingEntry_exception() {
        val col = TaskCollectionImpl()
        val task = Task("TaskId", "TaskName")
        val task2 = Task("TaskId2", "TaskName2")

        col.add(task)
        assertFailsWith<TaskNotFoundException> {
            col.remove(task2)
        }
    }
}
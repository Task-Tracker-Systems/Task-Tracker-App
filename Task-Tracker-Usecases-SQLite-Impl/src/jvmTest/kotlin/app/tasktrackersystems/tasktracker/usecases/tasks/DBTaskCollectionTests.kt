package app.tasktrackersystems.tasktracker.usecases.tasks

import app.tasktrackersystems.tasktracker.entities.Task
import app.tasktrackersystems.tasktracker.usecases.sqlite.Database
import app.tasktrackersystems.tasktracker.usecases.sqlite.InMemoryDriverFactory
import app.tasktrackersystems.tasktracker.usecases.sqlite.sqldelight.tasktracker.data.PersistentTask
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class DBTaskCollectionTests {

    @Test
    fun testEmptySize() {
        val collection = DBTaskCollection(Database(InMemoryDriverFactory().createDriver()))
        assertEquals(0, collection.size())
    }

    @Test
    fun testAdd() {
        val database = Database(InMemoryDriverFactory().createDriver())
        val collection = DBTaskCollection(database)
        collection.add(Task("Task", 5))

        // Collection handling
        assertEquals(1, collection.size())
        val task1 = collection.iterator().next()
        assertEquals("Task", task1.name)
        assertEquals(5, task1.totalDuration)

        // DB persistence
        val tasks = database.tasksQueries.selectAll().executeAsList()
        assertEquals(1, tasks.size)
        assertEquals(PersistentTask(1, "Task", 5), tasks[0])
    }

    @Test
    fun testAddMoreItems() {
        val database = Database(InMemoryDriverFactory().createDriver())
        val collection = DBTaskCollection(database)
        collection.add(Task("Task2", 20))
        collection.add(Task("Task", 5))

        // Collection handling
        assertEquals(2, collection.size())
        val it = collection.iterator()
        val task1 = it.next()
        assertEquals("Task", task1.name)
        assertEquals(5, task1.totalDuration)
        val task2 = it.next()
        assertEquals("Task2", task2.name)
        assertEquals(20, task2.totalDuration)

        // DB persistence
        val tasks = database.tasksQueries.selectAll().executeAsList()
        assertEquals(2, tasks.size)
        assertEquals(PersistentTask(2, "Task", 5), tasks[0])
        assertEquals(PersistentTask(1, "Task2", 20), tasks[1])
    }

    @Test
    fun testIteratorIsRefreshedOnCall() {
        val database = Database(InMemoryDriverFactory().createDriver())
        val collection = DBTaskCollection(database)
        collection.add(Task("Task", 5))
        collection.add(Task("Task2", 20))

        val it = collection.iterator()
        val it2 = collection.iterator()
        assertNotEquals(it, it2)

        assertTrue(it.hasNext())
        it.next()
        it.next()
        assertFalse(it.hasNext())
        assertTrue(it2.hasNext())
        it2.next()
        it2.next()
        assertFalse(it2.hasNext())
    }

    @Test
    fun testRemove() {
        val database = Database(InMemoryDriverFactory().createDriver())
        val collection = DBTaskCollection(database)
        collection.add(Task("Task", 5))
        collection.add(Task("Task2", 20))
        collection.add(Task("Task3", 13))

        // Collection handling
        assertEquals(3, collection.size())
        collection.remove(Task("Task2", 20))
        assertEquals(2, collection.size())
        val it = collection.iterator()
        assertEquals("Task", it.next().name)
        assertEquals("Task3", it.next().name)

        // DB persistence
        val tasks = database.tasksQueries.selectAll().executeAsList()
        assertEquals(2, tasks.size)
        assertEquals(PersistentTask(1, "Task", 5), tasks[0])
        assertEquals(PersistentTask(3, "Task3", 13), tasks[1])
    }

    @Test
    fun testReplace() {
        val database = Database(InMemoryDriverFactory().createDriver())
        val collection = DBTaskCollection(database)
        collection.add(Task("Task", 5))
        collection.replace(Task("Task", 20))

        // Collection handling
        assertEquals(1, collection.size())
        val task1 = collection.iterator().next()
        assertEquals("Task", task1.name)
        assertEquals(20, task1.totalDuration)

        // DB persistence
        val tasks = database.tasksQueries.selectAll().executeAsList()
        assertEquals(1, tasks.size)
        assertEquals(PersistentTask(1, "Task", 20), tasks[0])
    }
}

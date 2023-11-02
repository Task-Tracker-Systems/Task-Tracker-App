package app.tasktrackersystems.tasktracker.usecases.tasks

import app.tasktrackersystems.tasktracker.entities.Task
import app.tasktrackersystems.tasktracker.entities.TaskCollection
import app.tasktrackersystems.tasktracker.usecases.UseCases
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import io.mockative.once
import io.mockative.thenDoNothing
import io.mockative.verify
import kotlin.test.Test

class AddTaskTests {
    @Mock
    val collection = mock(classOf<TaskCollection>())

    @Test
    fun addOneEntry() {
        val task = Task("TaskName", 0)
        given(collection).invocation { add(task) }
            .thenDoNothing()

        UseCases.createUseCasesFromReaders(object : TaskCollectionReader {
            override fun read(): TaskCollection = collection
        }).createAddTask("TaskName").invoke()

        verify(collection).invocation { add(task) }
            .wasInvoked(exactly = once)
    }
}

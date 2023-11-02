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

class RemoveTaskTest {
    @Mock
    val collection = mock(classOf<TaskCollection>())

    @Test
    fun invoke_noInput_callsThrough() {
        val task = Task("TaskName", 0L)
        given(collection).invocation { remove(task) }
            .thenDoNothing()

        UseCases.createUseCasesFromReaders(object : TaskCollectionReader {
            override fun read(): TaskCollection = collection
        }).createRemoveTask(task).invoke()

        verify(collection).invocation { remove(task) }
            .wasInvoked(exactly = once)
    }
}

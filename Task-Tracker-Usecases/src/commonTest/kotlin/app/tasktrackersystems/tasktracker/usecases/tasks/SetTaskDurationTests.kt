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

class SetTaskDurationTests {
    @Mock
    val collection = mock(classOf<TaskCollection>())

    @Test
    fun setDuration() {
        val task = Task("TaskName", 0)
        val task2 = Task("TaskName", 5)
        given(collection).invocation { replace(task, task2) }
            .thenDoNothing()

        UseCases.createUseCasesFromReaders(object : TaskCollectionReader {
            override fun read(): TaskCollection = collection
        }).createSetTaskDuration(task, 5).invoke()

        verify(collection).invocation { replace(task, task2) }
            .wasInvoked(exactly = once)
    }

    @Test
    fun setDurationMultipleTimes() {
        val task = Task("TaskName", 0)
        val task2a = Task("TaskName", 5)
        val task2b = Task("TaskName", 10)
        given(collection).invocation { replace(task, task2a) }
            .thenDoNothing()
        given(collection).invocation { replace(task2a, task2b) }
            .thenDoNothing()

        val setDuration = UseCases.createUseCasesFromReaders(object : TaskCollectionReader {
            override fun read(): TaskCollection = collection
        }).createSetTaskDuration(task, 5)
        val setDuration2 = UseCases.createUseCasesFromReaders(object : TaskCollectionReader {
            override fun read(): TaskCollection = collection
        }).createSetTaskDuration(task, 10)

        setDuration.invoke()
        setDuration2.invoke()

        verify(collection).invocation { replace(task, task2a) }
            .wasInvoked(exactly = once)
        verify(collection).invocation { replace(task, task2b) }
            .wasInvoked(exactly = once)
    }
}

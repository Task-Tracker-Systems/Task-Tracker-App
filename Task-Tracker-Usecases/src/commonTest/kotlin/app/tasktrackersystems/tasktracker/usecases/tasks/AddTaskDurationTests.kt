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
import io.mockative.twice
import io.mockative.verify
import kotlin.test.Test

class AddTaskDurationTests {
    @Mock
    val collection = mock(classOf<TaskCollection>())

    @Test
    fun addDuration() {
        val task = Task("TaskName", 0)
        val task2 = Task("TaskName", 5)
        given(collection).invocation { replace(task, task2) }
            .thenDoNothing()

        UseCases.createUseCasesFromReaders(object : TaskCollectionReader {
            override fun read(): TaskCollection = collection
        }).createAddTaskDuration(task, 5).invoke()

        verify(collection).invocation { replace(task, task2) }
            .wasInvoked(exactly = once)
    }

    @Test
    fun addDurationMultipleTimes() {
        val task = Task("TaskName", 0)
        val task2a = Task("TaskName", 5)
        given(collection).invocation { replace(task, task2a) }
            .thenDoNothing()

        val addDuration = UseCases.createUseCasesFromReaders(object : TaskCollectionReader {
            override fun read(): TaskCollection = collection
        }).createAddTaskDuration(task, 5)

        addDuration.invoke()
        addDuration.invoke()

        verify(collection).invocation { replace(task, task2a) }
            .wasInvoked(exactly = twice)
    }
}

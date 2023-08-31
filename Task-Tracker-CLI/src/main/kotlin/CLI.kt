import com.garbereder.tasktracker.entities.DuplicateTaskException
import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.usecases.UseCases
import com.garbereder.tasktracker.usecases.tasks.TaskCollectionReaderFactory
import com.github.kinquirer.KInquirer
import com.github.kinquirer.components.promptConfirm
import com.github.kinquirer.components.promptInput
import com.github.kinquirer.components.promptList
import kotlinx.datetime.Clock
import kotlin.system.exitProcess

class CLI(
    private val taskCollectionReaderFactory: TaskCollectionReaderFactory
) {
    companion object {
        val back = "Return"

        fun addTask(useCases: UseCases): () -> Unit = {
            val taskName = KInquirer.promptInput("Task name:")
            if (taskName == back) {
                println("Illegal Task Name")
            } else {
                try {
                    useCases.createAddTask(taskName).invoke()
                } catch (e: DuplicateTaskException) {
                    println(e.message)
                }
            }
        }

        fun listTasks(useCases: UseCases): () -> Unit = {
            val taskIterator = useCases.createListTasks().invoke()
            val taskList = mutableMapOf<String, Task>()
            while (taskIterator.hasNext()) {
                val task = taskIterator.next()
                taskList["${task.name}(${task.totalDuration}s)"] = task
            }
            val options = taskList.keys.toMutableList()
            options.add(back)
            val selection: String = KInquirer.promptList("Select a task:", options)
            if (selection !== back) {
                val task = taskList[selection]!!
                when (KInquirer.promptList("What do you want ot do?", listOf("Start", "Delete"))) {
                    "Start" -> {
                        val start = Clock.System.now()
                        KInquirer.promptInput("${task.name} started. Finish now?")
                        val stop = Clock.System.now()
                        val duration = stop - start
                        useCases.createAddTaskDuration(task, duration.inWholeSeconds).invoke()
                        println("${task.name} started at $start and finished at $stop, tracked with ${duration.inWholeSeconds}s")
                    }

                    "Delete" -> {
                        val confirm = KInquirer.promptConfirm("Are you sure to delete $task")
                        if (confirm) {
                            useCases.createRemoveTasks(task).invoke()
                            println("$task was removed")
                        }
                    }
                }
            }
        }

        val quit: () -> Unit = {
            exitProcess(0)
        }
    }

    fun run() {
        println("Welcome to Task-Tracker-CLI")
        val useCases = UseCases.createUseCasesFromReaders(
            taskCollectionReaderFactory.create()
        )

        val choices = mapOf(
            "AddTask" to addTask(useCases),
            "ListTask" to listTasks(useCases),
            "Quit" to quit
        )
        while (true) {
            val selection: String = KInquirer.promptList("What do you want to do?", choices.keys.toList())
            choices[selection]!!.invoke()
        }
    }
}

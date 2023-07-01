import com.garbereder.tasktracker.entities.DuplicateTaskException
import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.usecases.activities.ActivityCollectionReaderFactory
import com.garbereder.tasktracker.usecases.activities.ActivityUseCases
import com.garbereder.tasktracker.usecases.tasks.TaskCollectionReaderFactory
import com.garbereder.tasktracker.usecases.tasks.TaskUseCases
import com.github.kinquirer.KInquirer
import com.github.kinquirer.components.promptConfirm
import com.github.kinquirer.components.promptInput
import com.github.kinquirer.components.promptList
import kotlinx.datetime.Clock
import kotlin.system.exitProcess

class CLI(
    private val taskCollectionReaderFactory: TaskCollectionReaderFactory,
    private val activityCollectionReaderFactory: ActivityCollectionReaderFactory
) {
    companion object {
        val back = "Return"

        fun addTask(taskUseCases: TaskUseCases): () -> Unit = {
            val taskName = KInquirer.promptInput("Task name:")
            if (taskName == back) {
                println("Illegal Task Name")
            } else {
                try {
                    taskUseCases.createAddTask(taskName).invoke()
                } catch (e: DuplicateTaskException) {
                    println(e.message)
                }
            }
        }

        fun listActivities(activityUseCases: ActivityUseCases): () -> Unit = {
            val actIt = activityUseCases.createListActivity().invoke()
            while (actIt.hasNext()) {
                println(actIt.next())
            }
        }

        fun listTasks(activityUseCases: ActivityUseCases, taskUseCases: TaskUseCases): () -> Unit = {
            val taskIterator = taskUseCases.createListTasks().invoke()
            val taskList = mutableMapOf<String, Task>()
            while (taskIterator.hasNext()) {
                val task = taskIterator.next()
                taskList[task.name] = task
            }
            val options = taskList.keys.toMutableList()
            options.add(back)
            val selection: String = KInquirer.promptList("Select a task:", options)
            if (selection !== back) {
                val task = taskList[selection]!!
                when (KInquirer.promptList("What do you want ot do?", listOf("Start", "Delete"))) {
                    "Start" -> {
                        val activity = activityUseCases.createStartActivity(task).invoke()
                        val start = Clock.System.now()
                        KInquirer.promptInput("${activity.task} started. Finish now?")
                        val stop = Clock.System.now()
                        val duration = stop - start
                        val stoppedActivity =
                            activityUseCases.createStopActivity(activity, duration.inWholeSeconds).invoke()
                        println("${stoppedActivity.task} started at $start and finished at $stop, tracked with ${stoppedActivity.durationInSeconds}s")
                    }

                    "Delete" -> {
                        val confirm = KInquirer.promptConfirm("Are you sure to delete $task")
                        if (confirm) {
                            taskUseCases.createRemoveTasks(task).invoke()
                            activityUseCases.createRemoveActivities(task).invoke()
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
        val useCases = TaskUseCases.createTaskUseCasesFromLoadTasks(taskCollectionReaderFactory.create()).invoke()
        val activityUseCases =
            ActivityUseCases.createActivityUseCasesFromLoadActivities(activityCollectionReaderFactory.create()).invoke()

        val choices = mapOf(
            "AddTask" to addTask(useCases),
            "ListTask" to listTasks(activityUseCases, useCases),
            "ListActivities" to listActivities(activityUseCases),
            "Quit" to quit
        )
        while (true) {
            val selection: String = KInquirer.promptList("What do you want to do?", choices.keys.toList())
            choices[selection]!!.invoke()
        }
    }
}

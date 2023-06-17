import com.garbereder.tasktracker.entities.*
import com.garbereder.tasktracker.usecases.tasks.*
import com.github.kinquirer.KInquirer
import com.github.kinquirer.components.promptConfirm
import com.github.kinquirer.components.promptInput
import com.github.kinquirer.components.promptList
import kotlin.system.exitProcess

fun main() {
    println("Welcome to Task-Tracker-CLI")
    val tasks = LoadTasks(object : TaskCollectionReader {
        override fun read(): TaskCollection {
            return TaskCollectionImpl()
        }
    }).invoke()

    val activities: ActivityCollection = ActivityCollectionImpl()

    val addTask: () -> Unit = {
        val taskName = KInquirer.promptInput("Task name:")
        AddTask(tasks, taskName).invoke()
    }

    val listTasks: () -> Unit = {
        val taskIterator = ListTasks(tasks).invoke()
        val taskList = mutableMapOf<String, Task>()
        while (taskIterator.hasNext()) {
            val task = taskIterator.next()
            taskList[task.name] = task
        }
        val selection: String = KInquirer.promptList("What do you want to start?", taskList.keys.toList())
        val activity = StartActivity(taskList[selection]!!).invoke()
        activities.add(activity)
        val stop = KInquirer.promptConfirm("${activity.task} started at ${activity.start}. Finish now?")
        if (stop) {
            val stoppedActivity = StopActivity(activity).invoke()
            activities.add(stoppedActivity)
            activities.remove(activity)
            println("${stoppedActivity.task} started at ${stoppedActivity.start} and finished at ${stoppedActivity.end}")
        }
    }

    val listActivities: () -> Unit = {
        val actIt = activities.iterator()
        while (actIt.hasNext()) {
            println(actIt.next())
        }
    }

    val quit: () -> Unit = {
        exitProcess(0)
    }

    val choices = mapOf(
        "AddTask" to addTask,
        "ListTask" to listTasks,
        "ListActivities" to listActivities,
        "Quit" to quit
    )
    while (true) {
        val selection: String = KInquirer.promptList("What do you want to do?", choices.keys.toList())
        choices[selection]!!.invoke()
    }

}
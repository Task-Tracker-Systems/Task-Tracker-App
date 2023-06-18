import com.garbereder.tasktracker.entities.ActivityCollection
import com.garbereder.tasktracker.entities.ActivityCollectionImpl
import com.garbereder.tasktracker.entities.Task
import com.garbereder.tasktracker.entities.TaskCollection
import com.garbereder.tasktracker.usecases.tasks.*
import com.github.kinquirer.KInquirer
import com.github.kinquirer.components.promptInput
import com.github.kinquirer.components.promptList
import kotlin.system.exitProcess

class CLI(private val taskCollectionReaderFactory: TaskCollectionReaderFactory) {
    companion object {
        val back = "Return"

        fun addTask(tasks: TaskCollection): () -> Unit = {
            val taskName = KInquirer.promptInput("Task name:")
            if (taskName == back) {
                println("Illegal Task Name")
            } else {
                AddTask(tasks, taskName).invoke()
            }
        }

        fun listActivities(activities: ActivityCollection): () -> Unit = {
            val actIt = activities.iterator()
            while (actIt.hasNext()) {
                println(actIt.next())
            }
        }

        fun listTasks(activities: ActivityCollection, tasks: TaskCollection): () -> Unit = {
            val taskIterator = ListTasks(tasks).invoke()
            val taskList = mutableMapOf<String, Task>()
            while (taskIterator.hasNext()) {
                val task = taskIterator.next()
                taskList["$task.name (${task.id})"] = task
            }
            val options = taskList.keys.toMutableList()
            options.add(back)
            val selection: String = KInquirer.promptList("What do you want to start?", options)
            if (selection !== back) {
                val activity = StartActivity(taskList[selection]!!).invoke()
                activities.add(activity)
                KInquirer.promptInput("${activity.task} started at ${activity.start}. Finish now?")
                val stoppedActivity = StopActivity(activity).invoke()
                activities.add(stoppedActivity)
                activities.remove(activity)
                println("${stoppedActivity.task} started at ${stoppedActivity.start} and finished at ${stoppedActivity.end}")
            }
        }

        val quit: () -> Unit = {
            exitProcess(0)
        }
    }

    fun run() {

        println("Welcome to Task-Tracker-CLI")
        val tasks = LoadTasks(taskCollectionReaderFactory.create()).invoke()

        val activities = LoadActivities(object : ActivityCollectionReader {
            override fun read(): ActivityCollection = ActivityCollectionImpl()
        }).invoke()

        val choices = mapOf(
            "AddTask" to addTask(tasks),
            "ListTask" to listTasks(activities, tasks),
            "ListActivities" to listActivities(activities),
            "Quit" to quit
        )
        while (true) {
            val selection: String = KInquirer.promptList("What do you want to do?", choices.keys.toList())
            choices[selection]!!.invoke()
        }
    }
}
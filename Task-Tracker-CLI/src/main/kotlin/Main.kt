
import com.garbereder.tasktracker.entities.TaskCollection
import com.garbereder.tasktracker.entities.TaskCollectionImpl
import com.garbereder.tasktracker.usecases.tasks.AddTask
import com.garbereder.tasktracker.usecases.tasks.ListTasks
import com.garbereder.tasktracker.usecases.tasks.LoadTasks
import com.garbereder.tasktracker.usecases.tasks.TaskCollectionReader
import com.github.kinquirer.KInquirer
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
    val addTask : (tasks: TaskCollection) -> Unit = {
        val taskName = KInquirer.promptInput("Task name:")
        AddTask(tasks, taskName).invoke()
    }
    val listTasks : (tasks: TaskCollection) -> Unit = {
            val taskIterator = ListTasks(tasks).invoke()
            while(taskIterator.hasNext()) {
                println(taskIterator.next().name)
            }
    }
    val quit : (tasks: TaskCollection) -> Unit = {
            exitProcess(0)
        }
    val choices = mapOf(
        "AddTask" to addTask,
        "ListTask" to listTasks,
        "Quit" to quit
    )
    while(true) {
        val selection: String = KInquirer.promptList("What do you want to do?", choices.keys.toList())
        choices[selection]!!.invoke(tasks)
    }

}
package app.tasktrackersystems.tasktracker.usecases.tasks

import app.tasktrackersystems.tasktracker.entities.Task
import app.tasktrackersystems.tasktracker.entities.TaskCollection
import app.tasktrackersystems.tasktracker.usecases.sqlite.Database

class DBTaskCollection(private val database: Database) :
    TaskCollection {

    override fun add(task: Task) =
        database.transaction {
            database.tasksQueries.insert(task.name, task.totalDuration)
        }

    override fun iterator(): Iterator<Task> =
        database.tasksQueries.selectAll().executeAsList().map { t -> Task(t.name, t.total_duration) }.iterator()

    override fun remove(task: Task) =
        database.transaction {
            database.tasksQueries.deleteByName(task.name)
        }

    override fun replace(task: Task) =
        database.transaction {
            database.tasksQueries.setTotalDuration(task.totalDuration, task.name)
        }

    override fun size(): Int = database.tasksQueries.selectAll().executeAsList().count()
}

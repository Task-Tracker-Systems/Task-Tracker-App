package app.tasktrackersystems.tasktracker.entities

interface TaskCollection : Iterable<Task> {
    fun add(task: Task)
    fun remove(task: Task)
    fun replace(task: Task, task2: Task)
    fun size(): Int
}

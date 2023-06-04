package com.garbereder.tasktracker.entities

class TaskCollectionImpl : TaskCollection {

    private val list = mutableListOf<Task>()
    override fun add(task: Task) {
        if (list.contains(task))
            throw DuplicateTaskException(task)
        list.add(task)
    }

    override fun iterator(): Iterator<Task> = list.iterator()

    override fun remove(task: Task) {
        if (!list.contains(task))
            throw TaskNotFoundException(task)
        list.remove(task)
    }

    override fun size(): Int = list.size

}

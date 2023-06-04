package com.garbereder.tasktracker.entities

interface TaskCollection: Iterable<Task> {
    fun add(task: Task)
    fun remove(task: Task)
    fun size(): Int
}
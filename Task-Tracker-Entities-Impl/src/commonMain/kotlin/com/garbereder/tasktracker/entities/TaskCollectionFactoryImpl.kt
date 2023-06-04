package com.garbereder.tasktracker.entities

import com.garbereder.tasktracker.usecases.tasks.TaskCollectionFactory

class TaskCollectionFactoryImpl : TaskCollectionFactory {
    override fun create(): TaskCollection = TaskCollectionImpl()

}
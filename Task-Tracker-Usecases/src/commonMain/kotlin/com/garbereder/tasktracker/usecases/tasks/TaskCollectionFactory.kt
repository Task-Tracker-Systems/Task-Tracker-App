package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.TaskCollection

interface TaskCollectionFactory {
    fun create(): TaskCollection
}


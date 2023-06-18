package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.TaskCollection

interface TaskCollectionReader {
    fun read(): TaskCollection
}

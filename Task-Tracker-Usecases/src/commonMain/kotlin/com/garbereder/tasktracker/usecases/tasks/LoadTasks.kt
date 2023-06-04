package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.TaskCollection
import com.garbereder.tasktracker.usecases.UseCase

class LoadTasks(private val reader: TaskCollectionReader) : UseCase {

    lateinit var tasks: TaskCollection

    override fun invoke() {
        tasks = this.reader.read()
    }

}
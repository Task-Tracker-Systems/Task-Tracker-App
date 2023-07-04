package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.usecases.UseCase

class LoadTasks(private val reader: TaskCollectionReader) : UseCase<TaskUseCases> {
    override fun invoke(): TaskUseCases = TaskUseCases(this.reader.read())
}

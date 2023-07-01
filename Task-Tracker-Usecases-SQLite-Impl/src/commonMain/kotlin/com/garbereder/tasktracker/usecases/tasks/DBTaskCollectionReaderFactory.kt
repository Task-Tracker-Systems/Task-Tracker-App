package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.usecases.tasks.TaskCollectionReader
import com.garbereder.tasktracker.usecases.tasks.TaskCollectionReaderFactory

expect class DBTaskCollectionReaderFactory : TaskCollectionReaderFactory {
    override fun create(): TaskCollectionReader
}

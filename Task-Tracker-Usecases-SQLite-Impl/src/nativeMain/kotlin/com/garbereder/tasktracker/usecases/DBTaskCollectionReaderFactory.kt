package com.garbereder.tasktracker.usecases

import com.garbereder.tasktracker.usecases.sqlite.DriverFactory
import com.garbereder.tasktracker.usecases.tasks.TaskCollectionReader
import com.garbereder.tasktracker.usecases.tasks.TaskCollectionReaderFactory

actual class DBTaskCollectionReaderFactory : TaskCollectionReaderFactory {
    actual override fun create(): TaskCollectionReader = DBTaskCollectionReader(DriverFactory())
}
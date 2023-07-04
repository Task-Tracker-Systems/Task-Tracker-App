package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.usecases.sqlite.DriverFactory

actual class DBTaskCollectionReaderFactory : TaskCollectionReaderFactory {
    actual override fun create(): TaskCollectionReader = DBTaskCollectionReader(DriverFactory())
}

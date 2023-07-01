package com.garbereder.tasktracker.usecases.activities

import com.garbereder.tasktracker.usecases.sqlite.DriverFactory
import com.garbereder.tasktracker.usecases.tasks.DBTaskCollectionReader
import com.garbereder.tasktracker.usecases.tasks.TaskCollectionReader
import com.garbereder.tasktracker.usecases.tasks.TaskCollectionReaderFactory

actual class DBActivityCollectionReaderFactory : ActivityCollectionReaderFactory {
    actual override fun create(): ActivityCollectionReader = DBActivityCollectionReader(DriverFactory())
}

package com.garbereder.tasktracker.usecases.activities

import com.garbereder.tasktracker.usecases.tasks.TaskCollectionReader
import com.garbereder.tasktracker.usecases.tasks.TaskCollectionReaderFactory

expect class DBActivityCollectionReaderFactory : ActivityCollectionReaderFactory {
    override fun create(): ActivityCollectionReader
}

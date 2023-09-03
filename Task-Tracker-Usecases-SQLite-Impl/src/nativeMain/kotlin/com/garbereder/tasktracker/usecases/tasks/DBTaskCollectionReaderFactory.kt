package com.garbereder.tasktracker.usecases.tasks

actual class DBTaskCollectionReaderFactory(private val factory: DBDriverFactory) : TaskCollectionReaderFactory {
    actual override fun create(): TaskCollectionReader = DBTaskCollectionReader(factory)
}

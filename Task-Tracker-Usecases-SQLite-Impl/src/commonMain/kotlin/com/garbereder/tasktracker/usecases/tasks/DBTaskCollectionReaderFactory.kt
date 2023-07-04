package com.garbereder.tasktracker.usecases.tasks

expect class DBTaskCollectionReaderFactory : TaskCollectionReaderFactory {
    override fun create(): TaskCollectionReader
}

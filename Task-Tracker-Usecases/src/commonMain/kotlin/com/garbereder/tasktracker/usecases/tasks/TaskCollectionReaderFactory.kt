package com.garbereder.tasktracker.usecases.tasks

interface TaskCollectionReaderFactory {
    fun create(): TaskCollectionReader
}
package com.garbereder.tasktracker.usecases.activities

expect class DBActivityCollectionReaderFactory : ActivityCollectionReaderFactory {
    override fun create(): ActivityCollectionReader
}

package com.garbereder.tasktracker.usecases.activities

import com.garbereder.tasktracker.usecases.sqlite.DriverFactory

actual class DBActivityCollectionReaderFactory : ActivityCollectionReaderFactory {
    actual override fun create(): ActivityCollectionReader = DBActivityCollectionReader(DriverFactory())
}

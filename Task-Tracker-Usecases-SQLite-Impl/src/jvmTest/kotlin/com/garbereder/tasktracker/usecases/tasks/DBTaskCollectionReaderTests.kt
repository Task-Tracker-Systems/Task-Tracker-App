package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.usecases.sqlite.InMemoryDriverFactory
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class DBTaskCollectionReaderTests {

    @Test
    fun testRead() {
        val reader = DBTaskCollectionReader(InMemoryDriverFactory())
        assertNotNull(reader.read())
    }
}
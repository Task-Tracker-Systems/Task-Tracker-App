package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.usecases.sqlite.InMemoryDriverFactory
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class DBTaskCollectionReaderFactoryTests {

    @Test
    fun testRead() {
        val fac = DBTaskCollectionReaderFactory(InMemoryDriverFactory())
        assertNotNull(fac.create())
    }
}

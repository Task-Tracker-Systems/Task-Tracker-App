package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.usecases.sqlite.DriverFactory
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class DriverFactoryTests {

    @Test
    fun testCreateDriver() {
        val fac = DriverFactory("jdbc:sqlite:tasktracker.sqlite")
        assertNotNull(fac.createDriver())
    }
}

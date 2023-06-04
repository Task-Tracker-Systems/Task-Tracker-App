package com.garbereder.tasktracker.entities

import kotlin.test.Test
import kotlin.test.assertTrue

class TaskCollectionFactoryImplTests {

    @Test
    fun create_inInput_isTaskCollection() {
        assertTrue {
            TaskCollectionFactoryImpl().create() is TaskCollection
        }
    }
}
package com.garbereder.tasktracker.usecases.tasks

import com.garbereder.tasktracker.entities.ActivityCollection

interface ActivityCollectionReader {
    fun read(): ActivityCollection
}

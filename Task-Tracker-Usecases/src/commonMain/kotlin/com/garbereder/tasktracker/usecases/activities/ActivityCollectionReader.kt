package com.garbereder.tasktracker.usecases.activities

import com.garbereder.tasktracker.entities.ActivityCollection

interface ActivityCollectionReader {
    fun read(): ActivityCollection
}

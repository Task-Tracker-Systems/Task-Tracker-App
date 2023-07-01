package com.garbereder.tasktracker.usecases.activities

import com.garbereder.tasktracker.usecases.UseCase

class LoadActivities(private val reader: ActivityCollectionReader) : UseCase<ActivityUseCases> {
    override fun invoke(): ActivityUseCases = ActivityUseCases(reader.read())
}

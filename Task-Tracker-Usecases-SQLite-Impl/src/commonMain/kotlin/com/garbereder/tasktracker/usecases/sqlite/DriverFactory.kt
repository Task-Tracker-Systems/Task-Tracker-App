package com.garbereder.tasktracker.usecases.sqlite

import app.cash.sqldelight.db.SqlDriver
import com.garbereder.tasktracker.usecases.tasks.DBDriverFactory

expect class DriverFactory : DBDriverFactory {
    override fun createDriver(): SqlDriver
}

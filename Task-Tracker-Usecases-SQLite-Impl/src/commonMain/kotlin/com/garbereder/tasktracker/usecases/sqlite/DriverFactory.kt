package com.garbereder.tasktracker.usecases.sqlite

import app.cash.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

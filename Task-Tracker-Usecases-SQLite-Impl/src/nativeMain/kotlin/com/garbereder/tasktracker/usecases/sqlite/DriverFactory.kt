package com.garbereder.tasktracker.usecases.sqlite

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver = NativeSqliteDriver(Database.Schema, "test.db")
}

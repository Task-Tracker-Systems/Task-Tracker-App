package com.garbereder.tasktracker.usecases.sqlite

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:tasktracker.sqlite")
            .apply {
                Database.Schema.create(this)
            }
        return driver
    }
}

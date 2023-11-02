package com.garbereder.tasktracker.usecases.sqlite

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.garbereder.tasktracker.usecases.tasks.DBDriverFactory

actual class DriverFactory(private val connectionString: String) : DBDriverFactory {
    actual override fun createDriver(): SqlDriver =
        JdbcSqliteDriver(connectionString)
            .apply {
                Database.Schema.create(this)
            }
}

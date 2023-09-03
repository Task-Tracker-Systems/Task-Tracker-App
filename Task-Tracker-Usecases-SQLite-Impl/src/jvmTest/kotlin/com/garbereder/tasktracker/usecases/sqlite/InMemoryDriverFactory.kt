package com.garbereder.tasktracker.usecases.sqlite

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.garbereder.tasktracker.usecases.tasks.DBDriverFactory

class InMemoryDriverFactory : DBDriverFactory {
    override fun createDriver(): SqlDriver =
        JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
            .apply {
                Database.Schema.create(this)
            }
}

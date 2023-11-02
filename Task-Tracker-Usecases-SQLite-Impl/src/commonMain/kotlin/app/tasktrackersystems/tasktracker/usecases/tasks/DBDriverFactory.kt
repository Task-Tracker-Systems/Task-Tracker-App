package app.tasktrackersystems.tasktracker.usecases.tasks

import app.cash.sqldelight.db.SqlDriver

interface DBDriverFactory {
    fun createDriver(): SqlDriver
}

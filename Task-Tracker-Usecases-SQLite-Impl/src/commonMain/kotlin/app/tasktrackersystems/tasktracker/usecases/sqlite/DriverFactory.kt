package app.tasktrackersystems.tasktracker.usecases.sqlite

import app.cash.sqldelight.db.SqlDriver
import app.tasktrackersystems.tasktracker.usecases.tasks.DBDriverFactory

expect class DriverFactory : DBDriverFactory {
    override fun createDriver(): SqlDriver
}

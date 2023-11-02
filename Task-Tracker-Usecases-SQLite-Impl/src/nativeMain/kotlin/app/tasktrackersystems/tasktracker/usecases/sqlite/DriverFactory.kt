package app.tasktrackersystems.tasktracker.usecases.sqlite

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import app.tasktrackersystems.tasktracker.usecases.tasks.DBDriverFactory

actual class DriverFactory(private val schema: SqlSchema<QueryResult.Value<Unit>>, private val name: String) :
    DBDriverFactory {
    actual override fun createDriver(): SqlDriver = NativeSqliteDriver(schema, name)
    // Database.Schema, "test.db"
}

package app.tasktrackersystems.tasktracker.usecases.tasks

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import app.tasktrackersystems.tasktracker.usecases.sqlite.Database
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class DBTaskCollectionReaderTests {

    @Test
    fun testRead() {
        val db = Database(
            JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
                .apply {
                    Database.Schema.create(this)
                }
        )
        val reader = DBTaskCollectionReader(db)
        assertNotNull(reader.read())
    }
}

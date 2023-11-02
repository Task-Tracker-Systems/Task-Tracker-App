package app.tasktrackersystems.tasktracker.usecases.tasks

import app.tasktrackersystems.tasktracker.usecases.sqlite.InMemoryDriverFactory
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class DBTaskCollectionReaderTests {

    @Test
    fun testRead() {
        val reader = DBTaskCollectionReader(InMemoryDriverFactory())
        assertNotNull(reader.read())
    }
}

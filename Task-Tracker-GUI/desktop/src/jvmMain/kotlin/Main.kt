import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import app.tasktrackersystems.tasktracker.common.App
import app.tasktrackersystems.tasktracker.usecases.UseCases
import app.tasktrackersystems.tasktracker.usecases.sqlite.Database
import app.tasktrackersystems.tasktracker.usecases.tasks.DBTaskCollectionReader
import java.nio.file.Files
import java.nio.file.Paths


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        val db = createJVMDatabase()
        val useCases = UseCases.createUseCasesFromReaders(DBTaskCollectionReader(db))
        App(useCases)
    }
}

fun createJVMDatabase() : Database {
    val userHome = System.getProperty("user.home")
    val dataDir = "${userHome}/.tasktrackersystems"
    if (!Files.isDirectory(Paths.get(dataDir))) {
        Files.createDirectory(Paths.get(dataDir))
    }
    val connectionString = "jdbc:sqlite:${dataDir}/tasktracker.sqlite"
    return Database(
        JdbcSqliteDriver(connectionString)
            .apply {
                Database.Schema.create(this)
            }
    )
}

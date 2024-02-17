
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import app.tasktrackersystems.tasktracker.usecases.sqlite.Database
import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val userHome = System.getProperty("user.home")
    val dataDir = "$userHome/.tasktrackersystems"
    if (!Files.isDirectory(Paths.get(dataDir))) {
        Files.createDirectory(Paths.get(dataDir))
    }
    val connectionString = "jdbc:sqlite:$dataDir/tasktracker.sqlite"
    CLI(
        Database(
            JdbcSqliteDriver(connectionString)
                .apply {
                    Database.Schema.create(this)
                }
        )
    ).run()
}

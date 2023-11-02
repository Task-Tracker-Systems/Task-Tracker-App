import app.tasktrackersystems.tasktracker.usecases.sqlite.DriverFactory
import app.tasktrackersystems.tasktracker.usecases.tasks.DBTaskCollectionReaderFactory

fun main() {
    CLI(
        DBTaskCollectionReaderFactory(DriverFactory("jdbc:sqlite:tasktracker.sqlite"))
    ).run()
}

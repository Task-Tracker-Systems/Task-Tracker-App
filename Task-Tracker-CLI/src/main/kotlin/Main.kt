import com.garbereder.tasktracker.usecases.sqlite.DriverFactory
import com.garbereder.tasktracker.usecases.tasks.DBTaskCollectionReaderFactory

fun main() {
    CLI(
        DBTaskCollectionReaderFactory(DriverFactory("jdbc:sqlite:tasktracker.sqlite"))
    ).run()
}

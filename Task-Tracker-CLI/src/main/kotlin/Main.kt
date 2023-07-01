import com.garbereder.tasktracker.usecases.activities.DBActivityCollectionReaderFactory
import com.garbereder.tasktracker.usecases.tasks.DBTaskCollectionReaderFactory

fun main() {
    CLI(
        DBTaskCollectionReaderFactory(),
        DBActivityCollectionReaderFactory()
    ).run()
}

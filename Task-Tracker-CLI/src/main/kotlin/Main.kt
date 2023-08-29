import com.garbereder.tasktracker.usecases.tasks.DBTaskCollectionReaderFactory

fun main() {
    CLI(
        DBTaskCollectionReaderFactory()
    ).run()
}

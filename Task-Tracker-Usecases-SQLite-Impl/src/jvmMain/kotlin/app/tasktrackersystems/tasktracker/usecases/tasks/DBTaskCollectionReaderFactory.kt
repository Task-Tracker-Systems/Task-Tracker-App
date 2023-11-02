package app.tasktrackersystems.tasktracker.usecases.tasks

actual class DBTaskCollectionReaderFactory(private val factory: DBDriverFactory) : TaskCollectionReaderFactory {
    actual override fun create(): TaskCollectionReader = DBTaskCollectionReader(factory)
    // DriverFactory("jdbc:sqlite:tasktracker.sqlite")
}

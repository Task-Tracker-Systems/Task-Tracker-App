package app.tasktrackersystems.tasktracker.usecases.tasks

expect class DBTaskCollectionReaderFactory : TaskCollectionReaderFactory {
    override fun create(): TaskCollectionReader
}

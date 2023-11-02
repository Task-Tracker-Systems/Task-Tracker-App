package app.tasktrackersystems.tasktracker.usecases

interface UseCase<out T> {
    fun invoke(): T
}

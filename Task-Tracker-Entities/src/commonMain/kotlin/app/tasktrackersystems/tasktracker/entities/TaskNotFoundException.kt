package app.tasktrackersystems.tasktracker.entities

class TaskNotFoundException(task: Task) : Exception("Could not find task: $task")

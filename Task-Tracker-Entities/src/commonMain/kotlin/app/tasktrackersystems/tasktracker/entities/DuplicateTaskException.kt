package app.tasktrackersystems.tasktracker.entities

class DuplicateTaskException(task: Task) : Exception("Could not add duplicate task: $task")

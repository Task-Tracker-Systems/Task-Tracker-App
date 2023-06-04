package com.garbereder.tasktracker.entities

class DuplicateTaskException(task: Task): Exception("Could not add duplicate task: $task") {
}
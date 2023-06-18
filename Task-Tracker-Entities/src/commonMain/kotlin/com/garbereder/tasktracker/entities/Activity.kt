package com.garbereder.tasktracker.entities

import kotlinx.datetime.LocalDateTime

data class Activity(
    val id: String,
    val start: LocalDateTime,
    val end: LocalDateTime?,
    val task: Task
)

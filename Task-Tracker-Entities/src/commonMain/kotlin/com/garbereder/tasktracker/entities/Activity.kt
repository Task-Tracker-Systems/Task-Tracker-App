package com.garbereder.tasktracker.entities

import kotlinx.datetime.LocalDateTime

data class Activity(
    val id: String,
    val startUTC: LocalDateTime,
    val endUTC: LocalDateTime,
    val task: Task
)
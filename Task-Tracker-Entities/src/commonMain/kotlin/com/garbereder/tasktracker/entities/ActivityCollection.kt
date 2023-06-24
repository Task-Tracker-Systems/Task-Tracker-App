package com.garbereder.tasktracker.entities

interface ActivityCollection : Iterable<Activity> {
    fun add(activity: Activity)
    fun remove(activity: Activity)
}

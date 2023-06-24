package com.garbereder.tasktracker.entities

class ActivityNotFoundException(activity: Activity) : Exception("Could not find activity: $activity")

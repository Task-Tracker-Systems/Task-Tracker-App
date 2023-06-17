package com.garbereder.tasktracker.entities

class ActivityCollectionImpl : ActivityCollection {

    private val list = mutableListOf<Activity>()
    override fun add(activity: Activity) {
        list.add(activity)
    }

    override fun iterator(): Iterator<Activity> = list.iterator()

    override fun remove(activity: Activity) {
        if (!list.contains(activity))
            throw ActivityNotFoundException(activity)
        list.remove(activity)
    }

}

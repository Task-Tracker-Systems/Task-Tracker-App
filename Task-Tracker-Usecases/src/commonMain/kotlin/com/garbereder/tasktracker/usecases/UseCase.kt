package com.garbereder.tasktracker.usecases

interface UseCase<out T> {
    fun invoke(): T
}
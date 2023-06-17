package com.garbereder.tasktracker.usecases.sqlite

import app.cash.sqldelight.db.SqlDriver

expect class DriverFactory {
  fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): Database {
  val driver = driverFactory.createDriver()
  val database = Database(driver)
  return database
}

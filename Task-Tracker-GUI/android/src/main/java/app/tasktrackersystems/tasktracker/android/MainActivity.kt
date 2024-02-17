package app.tasktrackersystems.tasktracker.android

import android.content.Context
import app.tasktrackersystems.tasktracker.common.App
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import app.tasktrackersystems.tasktracker.usecases.sqlite.Database

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                App(createAndroidDatabase(this))
            }
        }
    }
}

fun createAndroidDatabase(context: Context) : Database {
    return Database(AndroidSqliteDriver(Database.Schema, context))
}
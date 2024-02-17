package app.tasktrackersystems.tasktracker.common

import androidx.compose.runtime.Composable
import app.tasktrackersystems.tasktracker.usecases.UseCases
import app.tasktrackersystems.tasktracker.usecases.sqlite.Database
import app.tasktrackersystems.tasktracker.usecases.tasks.DBTaskCollectionReader
import cafe.adriel.voyager.navigator.Navigator

@Composable
fun App(db: Database) {

    val useCases = UseCases.createUseCasesFromReaders(DBTaskCollectionReader(db))

    Navigator(
        screen = HomeScreen(useCases),
        onBackPressed = { true }
    )
}


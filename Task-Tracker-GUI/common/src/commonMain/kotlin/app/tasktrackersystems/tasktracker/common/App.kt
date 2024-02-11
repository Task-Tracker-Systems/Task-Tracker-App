package app.tasktrackersystems.tasktracker.common

import androidx.compose.runtime.Composable
import app.tasktrackersystems.tasktracker.usecases.UseCases
import app.tasktrackersystems.tasktracker.usecases.sqlite.DriverFactory
import app.tasktrackersystems.tasktracker.usecases.tasks.DBTaskCollectionReaderFactory
import cafe.adriel.voyager.navigator.Navigator
import java.nio.file.Files
import java.nio.file.Paths

@Composable
fun App() {

    val userHome = System.getProperty("user.home")
    val dataDir = "${userHome}/.tasktrackersystems"
    if (!Files.isDirectory(Paths.get(dataDir))) {
        Files.createDirectory(Paths.get(dataDir))
    }
    val useCases = UseCases.createUseCasesFromReaders(
        DBTaskCollectionReaderFactory(DriverFactory("jdbc:sqlite:${dataDir}/tasktracker.sqlite")).create()
    )

    Navigator(
        screen = HomeScreen(useCases),
        onBackPressed = { true }
    )
}


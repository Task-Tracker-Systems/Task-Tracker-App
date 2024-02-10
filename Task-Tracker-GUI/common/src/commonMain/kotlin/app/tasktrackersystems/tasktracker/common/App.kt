package app.tasktrackersystems.tasktracker.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import app.tasktrackersystems.tasktracker.usecases.UseCases
import app.tasktrackersystems.tasktracker.usecases.sqlite.DriverFactory
import app.tasktrackersystems.tasktracker.usecases.tasks.DBTaskCollectionReaderFactory
import cafe.adriel.voyager.navigator.Navigator
import java.nio.file.Files
import java.nio.file.Paths
import java.security.MessageDigest
import kotlin.math.absoluteValue

@Composable
public fun App() {

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
        onBackPressed = {
            println("Navigator: Pop screen")
            true
        }
    )
}

fun String.toHslColor(saturation: Float = 0.75f, lightness: Float = 0.75f): Color {
    val bytes = this.toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    val hue = (digest.fold(0f) { acc, char -> char.toInt() * 13 + acc } % 360f).absoluteValue
    return Color.hsl(hue, saturation, lightness)
}

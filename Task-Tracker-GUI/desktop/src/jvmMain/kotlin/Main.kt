import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.tasktrackersystems.tasktracker.common.App


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}

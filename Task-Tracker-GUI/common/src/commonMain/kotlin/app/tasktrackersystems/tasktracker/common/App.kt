package app.tasktrackersystems.tasktracker.common

import androidx.compose.runtime.Composable
import app.tasktrackersystems.tasktracker.usecases.UseCases
import cafe.adriel.voyager.navigator.Navigator

@Composable
fun App(useCases: UseCases) {
    Navigator(
        screen = HomeScreen(useCases),
        onBackPressed = { true }
    )
}


package app.tasktrackersystems.tasktracker.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import app.tasktrackersystems.tasktracker.entities.Task
import app.tasktrackersystems.tasktracker.usecases.UseCases
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

data class TaskScreen(val useCases: UseCases, val task: Task) : Screen {
    @Composable
    override fun Content() {
        var taskName by remember { mutableStateOf(task.name) }
        var taskDuration by remember { mutableStateOf(task.totalDuration) }
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = { UI.header(taskName) }
        ) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = taskName,
                    onValueChange = { taskName = it },
                    label = { Text("Task Name") }
                )
                OutlinedTextField(
                    value = taskDuration.toString(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        try {
                            taskDuration = it.toLong()
                        } catch (_: Exception) {
                            // noop
                        }
                    },
                    label = { Text("Task Duration (s)") }
                )
                Button(
                    onClick = {
                        if (task.name != taskName) {
                            useCases.createRenameTask(task, taskName).invoke()
                        }
                        if (task.totalDuration != taskDuration) {
                            useCases.createSetTaskDuration(task, taskDuration).invoke()
                        }
                        navigator.pop()
                    }) {
                    Text("Save")
                }
            }
        }
    }
}
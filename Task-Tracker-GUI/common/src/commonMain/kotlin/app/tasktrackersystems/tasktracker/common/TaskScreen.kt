package app.tasktrackersystems.tasktracker.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.unit.dp
import app.tasktrackersystems.tasktracker.common.Utils.toTimeString
import app.tasktrackersystems.tasktracker.entities.Task
import app.tasktrackersystems.tasktracker.usecases.UseCases
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import java.util.*

data class TaskScreen(val useCases: UseCases, val task: Task) : Screen {
    @Composable
    override fun Content() {
        var taskName by remember { mutableStateOf(task.name) }
        var taskDuration by remember { mutableStateOf(task.totalDuration) }
        var timerRunning by remember { mutableStateOf(false) }
        var startStopButton by remember { mutableStateOf("Start") }
        var timer: Timer? = null
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
                if (!timerRunning) {
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
                } else {
                    Text(
                        modifier = Modifier.padding(0.dp, 23.dp, 0.dp, 22.dp),
                        text = taskDuration.toTimeString()
                    )
                }
                Row {
                    Button(
                        onClick = {
                            if(!timerRunning) {
                                startStopButton = "Stop"
                                timer = Timer()
                                timer?.scheduleAtFixedRate(object: TimerTask() {
                                    override fun run() {
                                        taskDuration += 1
                                    }

                                }, 0, 1000)
                                timerRunning = true
                            } else {
                                startStopButton = "Start"
                                timer?.cancel()
                                timer = null
                                timerRunning = false
                            }
                        }) {
                        Text(startStopButton)
                    }
                    Spacer(Modifier.width(5.dp))
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                        onClick = {
                            save(taskName, taskDuration)
                            navigator.pop()
                        }) {
                        Text("Save")
                    }
                }
            }
        }
    }

    private fun save(taskName: String, taskDuration: Long) {
        if (task.name != taskName) {
            useCases.createRenameTask(task, taskName).invoke()
        }
        if (task.totalDuration != taskDuration) {
            useCases.createSetTaskDuration(task, taskDuration).invoke()
        }
    }
}
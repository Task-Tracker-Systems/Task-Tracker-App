package app.tasktrackersystems.tasktracker.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.tasktrackersystems.tasktracker.common.Utils.toTimeString
import app.tasktrackersystems.tasktracker.entities.Task
import app.tasktrackersystems.tasktracker.usecases.UseCases
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import java.security.MessageDigest
import kotlin.math.absoluteValue

data class HomeScreen(val useCases: UseCases) : Screen {
    @Composable
    override fun Content() {
        val tasks = remember {
            mutableStateListOf<Task>()
        }
        useCases.createListTasks().invoke().forEach { task ->
            tasks.add(task)
        }
        Scaffold(
            topBar = { UI.header() },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    val taskName = "Task ${tasks.size + 1}"
                    tasks.add(useCases.createAddTask(taskName).invoke())
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }) {
            if (tasks.any()) {
                taskList(tasks, useCases)
            } else {
                empty()
            }
        }
    }

    @Composable
    fun empty() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("You have no tasks yet.", fontWeight = FontWeight.Bold)
            Text("Press the + button to add your first task.")
        }
    }

    @Composable
    fun taskList(tasks: SnapshotStateList<Task>, useCases: UseCases) {
        val navigator = LocalNavigator.currentOrThrow
        LazyColumn(
            Modifier.fillMaxSize()
        ) {
            itemsIndexed(tasks) { _, task ->
                Row(
                    Modifier
                        .fillMaxSize()
                        .padding(5.dp, 0.dp, 0.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    val taskShortName = task.name.substring(0, 1).uppercase()
                    Row(
                        Modifier
                            .fillMaxSize()
                            .padding(0.dp, 5.dp, 0.dp, 0.dp)
                            .clickable {
                                navigator.push(TaskScreen(useCases, task))
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(Modifier.size(32.dp), contentAlignment = Alignment.Center) {
                                Canvas(modifier = Modifier.fillMaxSize()) {
                                    drawCircle(task.name.toHslColor())
                                }
                                Text(
                                    text = taskShortName,
                                    color = Color.DarkGray
                                )
                            }
                            Spacer(Modifier.size(5.dp))
                            Column() {
                                Text(task.name)
                                Text(task.totalDuration.toTimeString(), color = Color.Gray)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun String.toHslColor(saturation: Float = 0.75f, lightness: Float = 0.75f): Color {
        val bytes = this.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        val hue = (digest.fold(0f) { acc, char -> char.toInt() * 13 + acc } % 360f).absoluteValue
        return Color.hsl(hue, saturation, lightness)
    }
}
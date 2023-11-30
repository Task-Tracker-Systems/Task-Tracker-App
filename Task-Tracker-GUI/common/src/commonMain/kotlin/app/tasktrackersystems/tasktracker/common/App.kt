package app.tasktrackersystems.tasktracker.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tasktrackersystems.tasktracker.entities.Task
import app.tasktrackersystems.tasktracker.usecases.UseCases
import app.tasktrackersystems.tasktracker.usecases.sqlite.*
import app.tasktrackersystems.tasktracker.usecases.tasks.DBTaskCollectionReaderFactory
import java.nio.file.Files
import java.nio.file.Paths

@Composable
fun App() {
    val userHome = System.getProperty("user.home")
    val dataDir = "${userHome}/.tasktrackersystems"
    if(!Files.isDirectory(Paths.get(dataDir))) {
        Files.createDirectory(Paths.get(dataDir))
    }
    val useCases = UseCases.createUseCasesFromReaders(
        DBTaskCollectionReaderFactory(DriverFactory("jdbc:sqlite:${dataDir}/tasktracker.sqlite")).create()
    )

    val tasks = remember {
        mutableStateListOf<Task>()
    }
    useCases.createListTasks().invoke().forEach { task ->
        tasks.add(task)
    }
    val interactionSource = remember { MutableInteractionSource() }
    var editTask by remember { mutableStateOf<String>("") }
    var trackTask by remember { mutableStateOf<String>("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Task Tracker GUI")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val taskName = "Task ${tasks.size + 1}"
                tasks.add(useCases.createAddTask(taskName).invoke())
                println(taskName)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }) {
        if (tasks.any()) {
            LazyColumn(
                Modifier.fillMaxSize()
            ) {
                itemsIndexed(tasks) { idx, task ->
                    Row(
                        Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                            .hoverable(interactionSource),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier.wrapContentWidth(Alignment.Start)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                if (task.name == editTask) {
                                    var taskName by remember { mutableStateOf(task.name) }
                                    OutlinedTextField(
                                        value = taskName,
                                        onValueChange = { v -> taskName = v },
                                        readOnly = false,
                                        textStyle = TextStyle(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier.padding(0.dp),
                                        label = { Text("Task Name") },
                                        trailingIcon = {
                                            IconButton(
                                                onClick = {
                                                    tasks[idx] = useCases.createRenameTask(task, taskName).invoke()
                                                    editTask = ""
                                                }
                                            ) {
                                                Icon(
                                                    Icons.Default.CheckCircle,
                                                    contentDescription = "",
                                                    tint = MaterialTheme.colors.primary
                                                )
                                            }
                                        }
                                    )
                                } else {
                                    Text(
                                        text = task.name,
                                        modifier = Modifier
                                            .padding(1.dp, 1.dp, 0.dp, 1.dp)
                                            .wrapContentWidth(Alignment.Start),
                                        fontSize = 18.sp,
                                        textAlign = TextAlign.Left,
                                        fontWeight = FontWeight.Bold,
                                    )
                                    Spacer(Modifier.size(5.dp))
                                    Icon(
                                        Icons.Default.Edit,
                                        contentDescription = "Edit",
                                        modifier = Modifier.size(16.dp).clickable {
                                            editTask = task.name
                                            trackTask = ""
                                        },
                                        tint = MaterialTheme.colors.secondary
                                    )
                                }
                            }
                            Text(
                                text = "Duration: ${task.totalDuration}",
                                modifier = Modifier
                                    .wrapContentWidth(Alignment.Start),
                                fontSize = 14.sp,
                                textAlign = TextAlign.Left
                            )
                        }
                        Spacer(Modifier.size(5.dp))
                        Row(
                            modifier = Modifier.wrapContentWidth(Alignment.End)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                if (task.name == trackTask) {
                                    var taskDuration by remember { mutableStateOf("") }
                                    OutlinedTextField(
                                        value = taskDuration,
                                        onValueChange = { v -> taskDuration = v },
                                        readOnly = false,
                                        textStyle = TextStyle(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
//                                    modifier = Modifier.padding(0.dp),
                                        label = { Text("Time to add") },
                                        trailingIcon = {
                                            IconButton(
                                                onClick = {
                                                    try {
                                                        tasks[idx] =
                                                            useCases.createAddTaskDuration(task, taskDuration.toLong())
                                                                .invoke()
                                                    } catch (e: Exception) {
                                                        // noop
                                                    } finally {
                                                        trackTask = ""
                                                    }
                                                }
                                            ) {
                                                Icon(
                                                    Icons.Default.CheckCircle,
                                                    contentDescription = "",
                                                    tint = MaterialTheme.colors.primary
                                                )
                                            }
                                        }
                                    )
                                } else {
                                    Button(
                                        onClick = {
                                            trackTask = task.name
                                            editTask = ""
                                        }) {
                                        Icon(Icons.Default.AddCircle, contentDescription = "Track Time")
                                    }
                                }
                                Spacer(Modifier.size(5.dp))
                                Button(
                                    onClick = {
                                        useCases.createRemoveTask(task).invoke()
                                        tasks.remove(task)
                                    }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                                }
                            }
                        }
                    }
                }
            }
        } else {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text("You have no tasks yet.", fontWeight = FontWeight.Bold)
                Text("Press the + button to add your first task.")
            }
        }
    }
}

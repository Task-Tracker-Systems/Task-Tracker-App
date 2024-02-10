package app.tasktrackersystems.tasktracker.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object UI {
    @Composable
    fun header(subtitle: String = "") {
        TopAppBar(
            title = {
                Row {
                    Box(Modifier.width(48.dp).height(24.dp)) {
                        val navigator = LocalNavigator.currentOrThrow
                        if (navigator.canPop) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = "Back",
                                Modifier.clickable {
                                    navigator.pop()
                                }
                            )
                        }
                    }
                    Text("Task Tracker GUI")
                    if(subtitle != "") {
                        Text(" - $subtitle")
                    }
                }
            }
        )
    }
}
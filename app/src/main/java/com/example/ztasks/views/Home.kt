package com.example.ztasks.views

import CheckList
import TaskViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ztasks.R
import com.example.ztasks.components.StatusWidget
import com.example.ztasks.data.models.LoginResponse
import com.example.ztasks.data.models.Task
import com.example.ztasks.data.models.Tasks
import com.example.ztasks.data.viewModels.HomeViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun Home(
    loginData: LoginResponse,
    homeViewModel: HomeViewModel = viewModel(),
    taskViewModel: TaskViewModel = viewModel()
) {
    var showDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val scaffoldState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var taskToDelete by remember { mutableStateOf<Task?>(null) }

    val tasks by homeViewModel.userTasks.observeAsState(initial = emptyList())
    val completedTasksCount = tasks.count { it.completed }

    val incompleteTasks = tasks.filter { !it.completed }

    LaunchedEffect(Unit) {
        homeViewModel.fetchUserTasks(loginData.id)
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = scaffoldState,
                snackbar = { data ->
                    Snackbar(
                        snackbarData = data,
                        contentColor = Color.White,
                        containerColor = Color.Red,
                        actionColor = Color.Yellow,
                        shape = MaterialTheme.shapes.medium
                    )
                }
            )
        },

        ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.logo_zt),
                        contentDescription = "zt logo",
                        modifier = Modifier.size(width = 70.dp, height = 70.dp)
                    )
                }
                Column {
                    Text(text = loginData.name, fontSize = 16.sp)
                }
            }
            Row {
                Text(
                    text = "Current Task",
                    fontSize = 20.sp,
                    style = TextStyle(color = colorResource(id = R.color.gray_600)),
                    fontWeight = FontWeight.Medium
                )
            }
            Divider(modifier = Modifier.height(10.dp), color = Color.Transparent)
            Row {
                StatusWidget(completedTasksCount)
            }
            Divider(modifier = Modifier.height(10.dp), color = Color.Transparent)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "My day",
                    fontSize = 20.sp,
                    style = TextStyle(color = colorResource(id = R.color.gray_600)),
                    fontWeight = FontWeight.Medium
                )
                IconButton(onClick = { showDialog = true }) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Agregar",
                        tint = colorResource(id = R.color.purple_600)
                    )
                }
            }
            Row {
                CheckList(
                    tasks = incompleteTasks,
                    onDelete = { task ->
                        taskToDelete = task
                        showDeleteDialog = true
                    },
                    onTaskClick = { task ->
                        val updatedTask = task.copy(completed = true)
                        println(updatedTask)
                        taskViewModel.updateTask(updatedTask.id, updatedTask) { success, updatedTask ->
                            if (success) {
                                homeViewModel.fetchUserTasks(loginData.id)
                            } else {
                                coroutineScope.launch {
                                    scaffoldState.showSnackbar(
                                        message = "Error al actualizar la tarea",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                        }
                    }
                )
            }
            }
            Divider(modifier = Modifier.height(10.dp), color = Color.Transparent)
        }

        if (showDialog) {
            Dialog(
                onDismissRequest = { showDialog = false },
                properties = DialogProperties()
            ) {
                Surface(
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Add new task")
                        Spacer(modifier = Modifier.padding(8.dp))
                        OutlinedTextField(
                            value = title,
                            onValueChange = { title = it },
                            label = { Text("Title") }
                        )
                        Spacer(modifier = Modifier.padding(8.dp))
                        OutlinedTextField(
                            value = description,
                            onValueChange = { description = it },
                            label = { Text("Description") }
                        )
                        Spacer(modifier = Modifier.padding(8.dp))
                        Row(
                            horizontalArrangement =  Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(onClick = {
                                val newTask = Task(
                                    title = title,
                                    description = description,
                                )
                                taskViewModel.createTask(newTask, loginData.id) { success, task ->
                                    if (success && task != null) {
                                        homeViewModel.fetchUserTasks(loginData.id)
                                    } else {
                                        coroutineScope.launch {
                                            scaffoldState.showSnackbar(
                                                message = "Error al crear la tarea",
                                                duration = SnackbarDuration.Short
                                            )
                                        }
                                    }
                                }
                                showDialog = false
                            }) {
                                Text("Save")
                            }
                            Button(onClick = { showDialog = false }) {
                                Text("Cancel")
                            }
                        }
                    }
                }
            }
        }
        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { Text(text = "Confirm elimination") },
                text = { Text(text = "¿Are you sure you want to delete this task?") },
                confirmButton = {
                    Button(onClick = {
                        taskToDelete?.let { task ->
                            taskViewModel.deleteTask(task.id) { success ->
                                if (success) {
                                    homeViewModel.fetchUserTasks(loginData.id)
                                } else {
                                    coroutineScope.launch {
                                        scaffoldState.showSnackbar(
                                            message = "Error al eliminar la tarea",
                                            duration = SnackbarDuration.Short
                                        )
                                    }
                                }
                            }
                        }
                        showDeleteDialog = false
                    }) {
                        Text("Delete")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDeleteDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }

@Composable
fun TaskItem(task: Task) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = task.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(text = task.description, fontSize = 14.sp)
        Divider(modifier = Modifier.height(8.dp), color = Color.Transparent)
    }
}

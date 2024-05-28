package com.example.ztasks.views

import CheckList
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ztasks.R
import com.example.ztasks.components.StatusWidget
import com.example.ztasks.data.models.Task
import com.example.ztasks.data.models.Tasks
import com.example.ztasks.data.viewModels.HomeViewModel

@Composable
fun Home(homeViewModel: HomeViewModel = viewModel()) {
    // Observa LiveData usando observeAsState
    val tasks by homeViewModel.userTasks.observeAsState(initial = emptyList())
    val completedTasksCount = tasks.count { it.completed }

    LaunchedEffect(Unit) {
        homeViewModel.fetchUserTasks(1)
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
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
                Text(text = "Fernando Gomez", fontSize = 16.sp)
                // Aquí dentro debería ir una imagen
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
        Row {
            StatusWidget(completedTasksCount)
        }
        Divider(modifier = Modifier.height(10.dp), color = Color.Transparent)
        Row {
            Text(
                text = "My day",
                fontSize = 20.sp,
                style = TextStyle(color = colorResource(id = R.color.gray_600)),
                fontWeight = FontWeight.Medium
            )
        }
        Row {
            CheckList(tasks = tasks, onDelete = { task -> /* Handle delete action */ })
        }
        Divider(modifier = Modifier.height(10.dp), color = Color.Transparent)
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

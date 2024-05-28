package com.example.ztasks.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ztasks.data.models.LoginResponse
import com.example.ztasks.data.viewModels.HomeViewModel
import com.example.ztasks.views.Home
import com.example.ztasks.views.Login
import kotlinx.coroutines.launch

@Composable
fun NavManager() {
    val navController = rememberNavController()
    var userData by remember { mutableStateOf<LoginResponse?>(null) }

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            Login(onNavigate = { data ->
                userData = data
                navController.navigate("home")
            })
        }
        composable("home") {
            userData?.let {
                Home(it)
            }
        }
    }
}



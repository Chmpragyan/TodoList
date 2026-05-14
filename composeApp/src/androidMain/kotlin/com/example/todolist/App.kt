package com.example.todolist

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todolist.navigation.AppNavigation
import com.example.todolist.navigation.Screen
import com.example.todolist.presentation.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(viewModel: TodoViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.TodoScreen.route

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(if (currentRoute == Screen.TodoScreen.route) Screen.TodoScreen.route else Screen.AddTodoScreen.route)
                    },
                    navigationIcon = {
                        if (currentRoute != Screen.TodoScreen.route) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back",
                                )
                            }
                        }
                    },
                    actions = {
                        if (currentRoute == Screen.TodoScreen.route) {
                            IconButton(onClick = {
                                viewModel.selectTodo(null)
                                navController.navigate(Screen.AddTodoScreen.route)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add Note"
                                )
                            }
                        }
                    },
                )
            }
        ) { paddingValues ->
            AppNavigation(viewModel, navController, paddingValues)
        }
    }
}
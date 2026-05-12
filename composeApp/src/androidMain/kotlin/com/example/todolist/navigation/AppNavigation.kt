package com.example.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist.AddTodoScreen
import com.example.todolist.TodoScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.TodoScreen.route
    ) {
        composable(Screen.TodoScreen.route) {
            TodoScreen(navController)
        }

        composable(Screen.AddTodoScreen.route) {
            AddTodoScreen(navController)
        }
    }
}
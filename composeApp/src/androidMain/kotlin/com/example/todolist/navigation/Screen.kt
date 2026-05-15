package com.example.todolist.navigation

sealed class Screen(val route: String) {
    object TodoScreen : Screen("Todo")
    object AddTodoScreen : Screen("Add Todo")
    object TodoDetailScreen : Screen("Todo Detail")
}
package com.example.todolist.navigation

sealed class Screen(val route: String) {
    object TodoScreen : Screen("todoScreen")
    object AddTodoScreen : Screen("addTodoScreen")
}
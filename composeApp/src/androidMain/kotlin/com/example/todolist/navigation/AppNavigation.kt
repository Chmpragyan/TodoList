package com.example.todolist.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todolist.AddTodoScreen
import com.example.todolist.TodoDetailScreen
import com.example.todolist.TodoScreen
import com.example.todolist.presentation.TodoViewModel

@Composable
fun AppNavigation(
    viewModel: TodoViewModel,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = Screen.TodoScreen.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(Screen.TodoScreen.route) {
            TodoScreen(
                todos = state.todos,
                onDelete = { id ->
                    viewModel.deleteTodo(id)
                },
                onEdit = { todo ->
                    viewModel.selectTodo(todo)
                    navController.navigate(Screen.AddTodoScreen.route)
                },
                onItemClick = { todo ->
                    viewModel.selectTodo(todo)
                    navController.navigate(Screen.TodoDetailScreen.route)
                }
            )
        }
        composable(Screen.AddTodoScreen.route) {
            AddTodoScreen(
                onAddNote = { title, description ->
                    viewModel.addTodo(title, description)
                    navController.popBackStack()
                },
                onUpdateNote = { id, title, description ->
                    viewModel.updateTodo(id, title, description)
                    navController.popBackStack()
                },
                todo = state.editTodo
            )
        }
        composable(Screen.TodoDetailScreen.route) {
            TodoDetailScreen(
                todo = state.editTodo
            )
        }
    }
}
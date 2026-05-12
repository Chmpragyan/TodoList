package com.example.todolist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.todolist.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(navController: NavController) {
    MaterialTheme {
        AppToolBar(
            onNextPage = {navController.navigate(Screen.AddTodoScreen.route)}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolBar(onNextPage: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Todo List") },
                actions = {
                    IconButton(onClick = {onNextPage()}) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Favorite Icon"
                        )

                    }
                },
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues))
    }
}
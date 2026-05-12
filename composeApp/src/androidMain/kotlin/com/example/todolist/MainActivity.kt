package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.navigation.AppNavigation
import com.example.todolist.presentation.TodoViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        viewModel = TodoViewModel(
            DatabaseDriverFactory(applicationContext)
        )

        setContent {
            App()
        }
    }
}

@Composable
@Preview
fun App(){
    MaterialTheme{
        AppNavigation()
    }
}
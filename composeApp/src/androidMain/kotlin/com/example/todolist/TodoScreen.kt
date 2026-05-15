package com.example.todolist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.data.model.TodoModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(
    todos: List<TodoModel>,
    onDelete: (Long) -> Unit,
    onEdit: (TodoModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp)
    ) {
        items(todos) { todo ->
            TodoItem(todo, onDelete, onEdit)
        }
    }
}

@Composable
fun TodoItem(
    todo: TodoModel,
    onDelete: (Long) -> Unit,
    onEdit: (TodoModel) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = todo.title, style = MaterialTheme.typography.titleLarge)
                Text(text = todo.todoDescription, style = MaterialTheme.typography.bodyMedium)
            }
            IconButton(onClick = { onEdit(todo) }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Todo"
                )
            }
            IconButton(onClick = { onDelete(todo.id) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Todo"
                )
            }
        }
    }
}
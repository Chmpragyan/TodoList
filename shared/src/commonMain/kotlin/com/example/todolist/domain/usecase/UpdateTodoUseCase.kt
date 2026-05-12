package com.example.todolist.domain.usecase

import com.example.todolist.data.repository.TodoRepository

class UpdateTodoUseCase(
    private val repository: TodoRepository
) {
    operator fun invoke(
        id: Long,
        title: String,
        description: String
    ) {
        repository.updateTodo(id, title, description)
    }
}
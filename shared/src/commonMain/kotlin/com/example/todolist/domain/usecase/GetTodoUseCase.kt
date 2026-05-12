package com.example.todolist.domain.usecase

import com.example.todolist.data.model.Todo
import com.example.todolist.data.repository.TodoRepository

class GetTodosUseCase(
    private val repository: TodoRepository
) {
    operator fun invoke(): List<Todo> {
        return repository.getTodos()
    }
}
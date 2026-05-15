package com.example.todolist.domain.usecase

import com.example.todolist.data.model.TodoModel
import com.example.todolist.data.repository.TodoRepository

class GetTodoUseCase(
    private val repository: TodoRepository
) {
    operator fun invoke(): List<TodoModel> {
        return repository.getTodos()
    }
}
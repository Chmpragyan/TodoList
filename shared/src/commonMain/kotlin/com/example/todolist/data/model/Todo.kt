package com.example.todolist.data.model

import kotlin.time.Clock
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class Todo(
    val id: String = Uuid.random().toString(),
    val title: String,
    val description: String,
    val createdAt: Long = Clock.System.now().toEpochMilliseconds()
)
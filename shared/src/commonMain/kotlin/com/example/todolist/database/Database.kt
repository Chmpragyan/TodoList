package com.example.todolist.database

import com.example.todolist.AppDatabase
import com.example.todolist.DatabaseDriverFactory

class Database(driverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(driver = driverFactory.createDriver())
    val todoQueries = database.todoQueries
}
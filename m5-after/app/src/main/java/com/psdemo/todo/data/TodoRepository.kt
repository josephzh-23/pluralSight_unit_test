package com.psdemo.todo.data

import androidx.lifecycle.LiveData

// this will be an interface here
interface TodoRepository {

    fun getAllTodos(): LiveData<List<Todo>>

    fun insert(todo: Todo)

    fun toggleTodo(id: String)

    fun getUpcomingTodosCount(): LiveData<Int>
}
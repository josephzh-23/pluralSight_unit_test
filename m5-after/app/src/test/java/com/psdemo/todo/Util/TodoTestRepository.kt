package com.psdemo.todo.Util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.psdemo.todo.data.Todo
import com.psdemo.todo.data.TodoRepository

/*
This actually works with returning mutable live data here
 */
class TodoTestRepository(private val todos: ArrayList<Todo>) : TodoRepository {
    override fun getAllTodos(): LiveData<List<Todo>> {
        return MutableLiveData(todos)
    }

    override fun insert(todo: Todo) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toggleTodo(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // We only want the tasks that are not completed
    override fun getUpcomingTodosCount(): LiveData<Int> {
        val count =
            todos.count {
                !it.completed &&
                        it.dueDate != null &&
                        it.dueDate!! >= System.currentTimeMillis()
            }
        return MutableLiveData(count)
    }
}
@file:Suppress("IncorrectScope")

package com.psdemo.todo

import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.psdemo.todo.data.Todo
import com.psdemo.todo.data.TodoRepository
import com.psdemo.todo.list.ListViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class ListViewModelTest {
    @get:Rule
    val exceptionRule = ExpectedException.none()
    val now = System.currentTimeMillis()
    val day = 1000 * 60 * 60 * 24

    @Test
    fun test_allTodosEmpty() {
        // Here we are making it return empty
        val repository: TodoRepository = mock()
        whenever(repository.getAllTodos())
            .thenReturn(MutableLiveData(arrayListOf()))
        val expected = 0
        val model = ListViewModel(repository)

        val todos = model.allTodos.value

        assertNotNull(todos)
        assertEquals(expected, todos!!.size)
    }




    @Test
    fun test_allTodosMultiple() {
        val repository: TodoRepository = mock(verboseLogging = true)

        var todo1 =  Todo("5", "Todo 5", now - day, false, now)
        var todo2 =  Todo("5", "Todo 5", now - day, false, now)
        var todo3 =  Todo("5", "Todo 5", now - day, false, now)

        var list = ArrayList<Todo>()
        list.add(todo1)
        list.add(todo2)
//        list.add(todo3)
        whenever(repository.getAllTodos())
            .thenReturn(
                MutableLiveData(
                    arrayListOf(
                        Todo("5", "Todo 5", now - day, false, now),
                        Todo("4", "Todo 4", now + day, true, now),
                        Todo("3", "Todo 3", now + day, false, now)
                    )
                )
            )

        whenever(repository.getAllTodos())
            .thenReturn(
                MutableLiveData(
                    list
                )
            )
        val expected = 2
        val model = ListViewModel(repository)

        val todos = model.allTodos.value

        assertNotNull(todos)
        assertEquals(expected, todos!!.size)
    }

    @Test
    fun test_allTodosSingle() {
        val repository: TodoRepository = mock()
        whenever(repository.getAllTodos())
            .thenReturn(
                MutableLiveData(
                    arrayListOf(
                        Todo("5", "Todo 5", now - day, false, now)
                    )
                )
            )
        val expected = 1
        val model = ListViewModel(repository)

        val todos = model.allTodos.value

        assertNotNull(todos)
        assertEquals(expected, todos!!.size)
    }

    @Test
    fun test_upcomingTodosCountEmpty() {
        val repository: TodoRepository = mock()
        val expected = 0
        whenever(repository.getUpcomingTodosCount())
            .thenReturn(MutableLiveData(expected))
        val model = ListViewModel(repository)

        val todoCount = model.upcomingTodosCount.value

        assertNotNull(todoCount)
        assertEquals(expected, todoCount)
    }

    @Test
    fun test_upcomingTodosCountSingle() {
        val repository: TodoRepository = mock()
        val expected = 1
        whenever(repository.getUpcomingTodosCount())
            .thenReturn(MutableLiveData(expected))
        val model = ListViewModel(repository)

        val todoCount = model.upcomingTodosCount.value

        assertNotNull(todoCount)
        assertEquals(expected, todoCount)
    }

    @Test
    fun test_upcomingTodosCountMultiple() {
        val repository: TodoRepository = mock()
        val expected = 5
        whenever(repository.getUpcomingTodosCount())
            .thenReturn(MutableLiveData(expected))
        val model = ListViewModel(repository)

        val todoCount = model.upcomingTodosCount.value

        assertNotNull(todoCount)
        assertEquals(expected, todoCount)
    }

    @Test
    fun test_ToggleTodo() {
        val repository: TodoRepository = mock()
        val id = "fake"
        val model = ListViewModel(repository)

        model.toggleTodo(id)

        verify(repository)
            .toggleTodo(id)
    }

    @Test
    fun test_ToggleTodoNotFound() {
        val repository: TodoRepository = mock()
        val id = "fake"
        val exceptionMessage = "Todo not found"
        whenever(repository.toggleTodo(id))
            .thenThrow(IllegalArgumentException(exceptionMessage))
        val model = ListViewModel(repository)
        exceptionRule.expect(java.lang.IllegalArgumentException::class.java)
        exceptionRule.expectMessage(exceptionMessage)

        model.toggleTodo(id)

        verify(repository)
            .toggleTodo(id)
    }
}
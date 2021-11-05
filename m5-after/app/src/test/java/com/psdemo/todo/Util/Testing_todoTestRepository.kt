package com.psdemo.todo.Util

import com.psdemo.todo.data.Todo
import com.psdemo.todo.data.TodoRepository
import com.psdemo.todo.list.ListViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

// How to test the fake todo Repository
public class ListViewModelTest2{



    @get:Rule
    val exceptionRule = ExpectedException.none()
    lateinit var repository: TodoRepository

     //

    @Before
    fun setup() {
        val now = System.currentTimeMillis()
        val day = 1000 * 60 * 60 * 24

        val todos = ArrayList<Todo>()
        var todo = Todo("1", "Todo 1", null, false, now)
        todos.add(todo)
        todo = Todo("2", "Todo 2", now + day, false, now)
        todos.add(todo)
        todo = Todo("3", "Todo 3", now + day, false, now)
        todos.add(todo)
        todo = Todo("4", "Todo 4", now + day, true, now)
        todos.add(todo)
        todo = Todo("5", "Todo 5", now - day, false, now)
        todos.add(todo)

        repository = TodoTestRepository(todos)
    }

    @Test
    fun test_allTodos(){
        val expected = 5
        val model = ListViewModel(repository)

        // Returning the mutable live data value here
        val todos = model.allTodos.value
        assertNotNull(todos)
        assertEquals(expected, todos!!.size)
    }

    // And this is it
    @Test
    fun test_ToggleTodo() {
        val id = "fake"
        val model = ListViewModel(repository)

        // This correlates with the rule defined above
        /*
        Because the method toggletodo () has not been implemented yet,

         */
        exceptionRule.expect(NotImplementedError::class.java)

        model.toggleTodo(id)
    }



}


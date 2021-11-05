package com.psdemo.todo

import com.psdemo.todo.list.determineCardColor
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized



@RunWith(Parameterized::class)
class ListUtilsTest(
    private val expected: Int,
    private val dueDate: Long?,
    private val done: Boolean,
    private val scenario: String
) {
    companion object {
        val now = System.currentTimeMillis()
        val day = 1000 * 60 * 60 * 24

        // Using this we can run 3 tests passing different
        /*
           the 3 represents the values that we used above
           - expected, due date and done and that's it
           - this will then run 3 tests as below
         */
        @JvmStatic
        @Parameterized.Parameters(name = "determineCardColor: {3}")
        fun todos() = listOf(
            arrayOf(R.color.todoDone, null, true, "Done, no date"),
            arrayOf(R.color.todoNotDue, null, false, "Not done, no date"),
            arrayOf(R.color.todoOverDue, now - day, false, "Not done, due yesterday")
        )
    }

    @Test
    fun test_determineCardColor() {
        val actual = determineCardColor(dueDate, done)

        assertEquals(expected, actual)
    }


    @Test
    fun test_determinCardColorNotDone(){

        val expected = R.color.todoNotDue
        val dueDate = null
        val done = false
        val actual = determineCardColor(dueDate, done)

        assertEquals(expected, actual)
    }

    @Test



    // Set the test date to yesterday
    fun test_determinCardColorOverdue(){
        val expected = R.color.todoOverDue
        val dueDate = now - day

        val done = false

    }

}
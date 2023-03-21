package com.example.nelsonmartinez_nycschools.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nelsonmartinez_nycschools.data.models.Schools
import com.example.nelsonmartinez_nycschools.data.models.Scores
import com.example.nelsonmartinez_nycschools.data.repo.Repository
import com.example.nelsonmartinez_nycschools.utils.NetworkResult
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SchoolViewModelTest {

    //this rule allows to mock the main looper (main thread) to execute any task right away
    @get:Rule val rule = InstantTaskExecutorRule()

    private lateinit var test: SchoolViewModel

    // mocking the repository to provide mock response to the data that will be tested
    private val mockRepo = mockk<Repository>(relaxed = true)

    // I need the test dispatcher for the coroutines
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        //setting the main dispatcher
        Dispatchers.setMain(testDispatcher)

        // initialising the test object needed to get the scores

        test = SchoolViewModel(mockRepo)
    }

    @After
    fun tearDown() {
        // resetting the main thread
        Dispatchers.resetMain()
        // clearing memory after the test is done
        clearAllMocks()
    }

    @Test
    fun `get schools when the server retrieves a valid response returns a success state`() {
        // ASSIGNING THE DATA NEEDED
        val school = Schools().apply {
            add(mockk())
            add(mockk())
        }
        every { mockRepo.getSchools() } returns flowOf(
            NetworkResult.Success(school)
        )

        // initialising the viewmodel since getSchools() is in the init method
        val testObject = SchoolViewModel(mockRepo)

        // observing the live data to retrieve the schools
        testObject.schoolList.observeForever {
            if (it is NetworkResult.Success) {
                // asserting the type success and size of the list is 2
                assertNotNull(it.data)
                assertEquals(2, it.data?.size)
            }
        }
    }

    @Test
    fun `get schools when the server retrieves invalid response returns a error state`() {
        // ASSIGNING THE DATA NEEDED
        every { mockRepo.getSchools() } returns flowOf(
            NetworkResult.Error("Error")
        )

        // initialising the viewmodel since the get schools is in the init method
        val testObject = SchoolViewModel(mockRepo)

        // observing the live data to retrieve the schools
        testObject.schoolList.observeForever {
            if (it is NetworkResult.Error) {
                assertNotNull(it.message)
                assertEquals("Error", it.message)
            }
        }
    }

    @Test
    fun `get scores when the server retrieves valid list response returns a success state`() {
        // ASSIGNING THE DATA NEEDED
        every { mockRepo.getScores("123") } returns flowOf(
            NetworkResult.Success(mockk(relaxed = true) {
                every { dbn } returns "123"
            })
        )

        // set the selected school to retrieve the scores
        test.selectedSchool = mockk {
            every { dbn } returns "123"
        }

        // observing the live data to retrieve the score
        test.scoresList.observeForever {
            if (it is NetworkResult.Success) {
                assertNotNull(it.data)
                assertEquals("123", it.data?.dbn)
            }
        }

        test.searchScores()
    }

    @Test
    fun `get scores when no school selected returns a error state`() {
        // ASSIGNING THE DATA NEEDED
        // set the selected school to retrieve the scores
        test.selectedSchool = null

        // observing the live data to retrieve the score
        test.scoresList.observeForever {
            if (it is NetworkResult.Error) {
                assertNotNull(it.message)
                assertEquals("no schools selected", it.message)
            }
        }

        test.searchScores()
    }

    @Test
    fun `get scores when school selected but no dbn provided returns a error state`() {
        // ASSIGNING THE DATA NEEDED
        // set the selected school to retrieve the scores
        test.selectedSchool = mockk {
            every { dbn } returns null
        }

        // observing the live data to retrieve the score
        test.scoresList.observeForever {
            if (it is NetworkResult.Error) {
                assertNotNull(it.message)
                assertEquals("dbn is null", it.message)
            }
        }

        test.searchScores()
    }
}
package com.example.testapp

import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseUnitTest {

    protected val testDispatcher = StandardTestDispatcher()

    @Before
    open fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    open fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    protected fun <T> testFlow(
        flow: Flow<T>,
        action: suspend TestScope.() -> Unit = {},
        expectation: suspend (result: List<T>) -> Unit
    ) {
        runTest(testDispatcher) {
            val results = mutableListOf<T>()
            val job = launch { flow.toList(results) }

            advanceUntilIdle() // wait for flow will be subscribed
            action()
            advanceUntilIdle()

            expectation(results)
            job.cancel()
        }
    }
}
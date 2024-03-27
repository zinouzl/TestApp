package com.example.testapp.ui.auth

import androidx.compose.ui.text.input.TextFieldValue
import com.example.domain.model.User
import com.example.domain.usecase.GetUserUseCase
import com.example.testapp.BaseUnitTest
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlin.test.Test
import kotlin.test.assertIs

private const val FAKE_USER_ID = 23
private val FAKE_USER = User(
    id = 23,
    userName = "Terrell Greer",
    email = "elise.cain@example.com",
    website = "habitasse"
)

class AuthViewModelTest : BaseUnitTest() {

    private val mockedGetUserUseCase: GetUserUseCase = mockk()


    //    private val testSavedStateHandle = SavedStateHandle(
//        mapOf("user" to testDeviceId)
//    )
    private lateinit var viewModel: AuthViewModel


    override fun setUp() {
        super.setUp()
        viewModel = AuthViewModel(
            mockedGetUserUseCase
        )
    }

    @Test
    fun `should show init content`() {
        val expectedContent = AuthViewModel.State.Content()
        testFlow(
            flow = viewModel.state
        ) { results ->
            val content = results.lastOrNull()
            assertIs<AuthViewModel.State.Content>(content)
            assertEquals(expectedContent, content)
        }
    }

    @Test
    fun `should update text content`() {
        val expectedContent = AuthViewModel.State.Content(
            userInput = TextFieldValue(text = FAKE_USER_ID.toString())
        )

        testFlow(flow = viewModel.state,
            action = { viewModel.onValueChanged(TextFieldValue(text = FAKE_USER_ID.toString())) }
        ) { results ->
            val content = results.lastOrNull()
            assertIs<AuthViewModel.State.Content>(content)
            assertEquals(expectedContent, content)
        }
    }

    @Test
    fun `should navigate to posts screen`() {
        coEvery { mockedGetUserUseCase.invoke(any()) } returns Result.success(FAKE_USER)

        testFlow(flow = viewModel.event,
            action = {
                viewModel.onValueChanged(TextFieldValue(text = FAKE_USER_ID.toString()))
                viewModel.onClick()
            }
        ) { results ->
            val actualEvent = results.firstOrNull()
            assertEquals(actualEvent, AuthViewModel.Event.Navigation.UserValidated(FAKE_USER_ID, FAKE_USER.email))
        }
    }

    @Test
    fun `should show error if throwable is thrown`() {
        runCatching {
            val throwable = Throwable()
            coEvery { mockedGetUserUseCase.invoke(any()) } throws throwable

            val expectedContent = AuthViewModel.State.Content(
                isOnError = true
            )

            testFlow(
                viewModel.state
            ) { results ->
                assertEquals(expectedContent, results.lastOrNull())
            }
        }
    }
}
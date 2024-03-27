package com.example.testapp.ui.posts

import androidx.lifecycle.SavedStateHandle
import com.example.domain.model.Post
import com.example.domain.usecase.GetUserPostsUseCase
import com.example.testapp.BaseUnitTest
import com.example.testapp.ui.base.composenavigation.Screen
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.mockk
import junit.framework.TestCase
import org.junit.Test
import kotlin.test.assertEquals

private const val FAKE_USER_ID = 23
private const val FAKE_EMAIL = "email@email.com"
private val FAKE_POSTS = listOf<Post>(
    Post(userId = FAKE_USER_ID, id = 7231, title = "propriae", body = "ubique")
)

class PostViewModelTest : BaseUnitTest() {

    private val mockedGetUserPosts: GetUserPostsUseCase = mockk()

    private lateinit var viewModel: PostViewModel

    private val mockedSavedStateHandle = SavedStateHandle(
        mapOf(
            Screen.USER_ID to FAKE_USER_ID,
            Screen.USER_EMAIL to FAKE_EMAIL
        )
    )

    override fun setUp() {
        super.setUp()
        viewModel = PostViewModel(
            savedStateHandle = mockedSavedStateHandle,
            getUserPostsUseCase = mockedGetUserPosts
        )
    }

    @Test
    fun `should show no list if user do not have any post`() {
        coEvery { mockedGetUserPosts.invoke(GetUserPostsUseCase.Params(FAKE_USER_ID)) } returns Result.success(emptyList<Post>())

        val expectedContent = PostViewModel.State.Content(
            userEmail = FAKE_EMAIL,
            postList = listOf(),
            isLoading = false
        )
        testFlow(
            flow = viewModel.state
        ) { results ->
            val actual = results.lastOrNull()
            assertEquals(expectedContent, actual)
        }
    }

    @Test
    fun `should show post if user has them`() {
        coEvery { mockedGetUserPosts.invoke(GetUserPostsUseCase.Params(FAKE_USER_ID)) } returns Result.success(FAKE_POSTS)

        val expectedContent = PostViewModel.State.Content(
            userEmail = FAKE_EMAIL,
            postList = FAKE_POSTS,
            isLoading = false
        )
        testFlow(
            flow = viewModel.state
        ) { results ->
            val actual = results.lastOrNull()
            assertEquals(expectedContent, actual)
        }
    }

    @Test
    fun `should navigate back when back is clicked`() {
        coJustRun { mockedGetUserPosts.invoke(GetUserPostsUseCase.Params(FAKE_USER_ID)) }

        testFlow(
            flow = viewModel.event,
            action = { viewModel.onBackClicked() }
        ) { results ->
            val actual = results.firstOrNull()
            TestCase.assertEquals(
                actual,
                PostViewModel.Event.Navigation.Back
            )
        }
    }
}
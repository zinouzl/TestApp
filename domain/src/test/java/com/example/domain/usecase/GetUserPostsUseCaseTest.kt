package com.example.domain.usecase

import com.example.domain.model.Post
import com.example.domain.repository.PostRepository
import com.example.domain.usecase.base.BaseTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

private const val mockUserId = 4943
private val mockParams = GetUserPostsUseCase.Params(userId = mockUserId)
private val mockedPosts = listOf(
    Post(userId = 4943, id = 8206, title = "suas", body = "commune"),
    Post(userId = 4943, id = 8517, title = "melius", body = "simul"),
    Post(userId = 4943, id = 4889, title = "omittantur", body = "consetetur")
)

class GetUserPostsUseCaseTest : BaseTest() {

    // class under test
    private lateinit var getUserPostsUseCase: GetUserPostsUseCase

    private val mockRepository: PostRepository = mockk()

    override fun setUp() {
        super.setUp()
        getUserPostsUseCase = GetUserPostsUseCase(
            dispatcher = testDispatcher,
            postRepository = mockRepository
        )
    }

    @Test
    fun `should return user posts if there is any`() = runTest {
        coEvery { mockRepository.getPostsOfUser(mockUserId) } returns mockedPosts

        val result = getUserPostsUseCase(mockParams)
        assertTrue(result.isSuccess)
        coVerify(exactly = 1) { mockRepository.getPostsOfUser(mockUserId) }
        assertEquals(mockedPosts, result.getOrNull())
    }

    @Test
    fun `should return error if there is an exception`() = runTest {
        val exception = IllegalStateException()
        coEvery { mockRepository.getPostsOfUser(mockUserId) } throws exception

        val result = getUserPostsUseCase(mockParams)
        coVerify(exactly = 1) { mockRepository.getPostsOfUser(mockUserId) }
        assertEquals(exception, result.exceptionOrNull())
    }
}
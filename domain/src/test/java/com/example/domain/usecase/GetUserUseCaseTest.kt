package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.AuthRepository
import com.example.domain.usecase.base.BaseTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

private const val testUserId = 19384
private val mockUser = User(
    id = 19384,
    userName = "Lisa Austin",
    email = "louis.conner@example.com",
    website = "porta"
)
private val testParams = GetUserUseCase.Params(testUserId)

class GetUserUseCaseTest : BaseTest() {

    // the class under test
    private lateinit var getUserUseCaseTest: GetUserUseCase

    private val mockAuthRepository: AuthRepository = mockk()

    override fun setUp() {
        super.setUp()
        getUserUseCaseTest = GetUserUseCase(
            dispatcher = testDispatcher,
            repository = mockAuthRepository
        )
    }

    @Test
    fun `should return the user if it exists`() = runTest {
        coEvery { mockAuthRepository.getUser(testUserId) } returns mockUser

        val result = getUserUseCaseTest(testParams)
        assertTrue(result.isSuccess)
        assert(result.getOrNull() == mockUser)
        coVerify(exactly = 1) { mockAuthRepository.getUser(testUserId) }
    }

    @Test
    fun `should return error if user does not exist`() = runTest {
        val exception = IllegalStateException()
        coEvery { mockAuthRepository.getUser(testUserId) } throws exception

        val result = getUserUseCaseTest(testParams)
        assertEquals(exception, result.exceptionOrNull())
        coVerify(exactly = 1) { mockAuthRepository.getUser(testUserId) }
    }

}
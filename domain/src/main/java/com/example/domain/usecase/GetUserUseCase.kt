package com.example.domain.usecase

import com.example.domain.common.SuspendingUseCase
import com.example.domain.common.UseCaseParams
import com.example.domain.model.User
import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetUserUseCase(
    override val dispatcher: CoroutineDispatcher,
    private val repository: AuthRepository
) : SuspendingUseCase<GetUserUseCase.Params, User>() {

    override suspend fun run(params: Params): Result<User> = runCatching {
        repository.getUser(params.userId)
    }

    @JvmInline
    value class Params(
        val userId: Int
    ) : UseCaseParams
}
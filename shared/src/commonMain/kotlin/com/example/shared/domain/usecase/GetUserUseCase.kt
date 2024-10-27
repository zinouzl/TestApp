package com.example.shared.domain.usecase

import com.example.shared.domain.common.SuspendingUseCase
import com.example.shared.domain.common.UseCaseParams
import com.example.shared.domain.model.User
import com.example.shared.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.jvm.JvmInline

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
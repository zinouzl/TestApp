package com.example.shared.domain.usecase

import com.example.shared.domain.common.SuspendingUseCase
import com.example.shared.domain.common.UseCaseParams
import com.example.shared.domain.model.Post
import com.example.shared.domain.repository.PostRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlin.jvm.JvmInline

class GetUserPostsUseCase(
    override val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val postRepository: PostRepository
) : SuspendingUseCase<GetUserPostsUseCase.Params, List<Post>>() {

    override suspend fun run(params: Params): Result<List<Post>> = runCatching {
        postRepository.getPostsOfUser(params.userId)
    }

    @JvmInline
    value class Params(val userId: Int) : UseCaseParams
}
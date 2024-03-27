package com.example.domain.usecase

import com.example.domain.common.SuspendingUseCase
import com.example.domain.common.UseCaseParams
import com.example.domain.model.Post
import com.example.domain.repository.PostRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetUserPostsUseCase @Inject constructor(
    override val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val postRepository: PostRepository
) : SuspendingUseCase<GetUserPostsUseCase.Params, List<Post>>() {

    override suspend fun run(params: Params): Result<List<Post>> = runCatching {
        postRepository.getPostsOfUser(params.userId)
    }

    @JvmInline
    value class Params(val userId: Int) : UseCaseParams
}
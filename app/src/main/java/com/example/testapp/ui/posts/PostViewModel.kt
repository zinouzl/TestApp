package com.example.testapp.ui.posts

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Post
import com.example.domain.usecase.GetUserPostsUseCase
import com.example.testapp.ui.base.ViewModelEvent
import com.example.testapp.ui.base.ViewModelEventDelegate
import com.example.testapp.ui.base.ViewModelEventDelegateImpl
import com.example.testapp.ui.base.composenavigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getUserPostsUseCase: GetUserPostsUseCase
) : ViewModel(),
    ViewModelEventDelegate<PostViewModel.Event> by ViewModelEventDelegateImpl() {

    sealed interface Event : ViewModelEvent {

        sealed interface Navigation : Event {

            data object Back : Navigation
        }
    }

    @Immutable
    sealed interface State {

        @Stable
        data class Content(
            val userEmail: String,
            val postList: List<Post>,
            val isLoading: Boolean
        ) : State

        @Stable
        data object Empty : State
    }

    private val _userEmail = MutableStateFlow("")
    private val _postList = MutableStateFlow(emptyList<Post>())
    private val _isLoading = MutableStateFlow(false)

    val state: StateFlow<State> by lazy {
        combine(
            _userEmail,
            _postList,
            _isLoading,
            State::Content
        ).onStart { initValues() }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), State.Empty)
    }

    private fun initValues() {
        viewModelScope.launch {
            _isLoading.update { true }
            _userEmail.update { savedStateHandle.get<String>(Screen.USER_EMAIL) ?: "" }
            val userId = savedStateHandle.get<Int>(Screen.USER_ID) ?: -1

            getUserPostsUseCase(GetUserPostsUseCase.Params(userId = userId))
                .onSuccess { list -> _postList.update { list } }
                .onFailure { it.printStackTrace() }
            _isLoading.update { false }
        }
    }

    fun onBackClicked() = sendEvents(Event.Navigation.Back)

}
package com.example.testapp.ui.auth

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetUserUseCase
import com.example.testapp.ui.base.ViewModelEvent
import com.example.testapp.ui.base.ViewModelEventDelegate
import com.example.testapp.ui.base.ViewModelEventDelegateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel(), ViewModelEventDelegate<AuthViewModel.Event> by ViewModelEventDelegateImpl() {

    sealed interface Event : ViewModelEvent {

        sealed interface Navigation : Event {

            data class UserValidated(
                val userId: Int,
                val userEmail: String
            ) : Navigation
        }
    }

    @Immutable
    sealed interface State {

        @Stable
        data class Content(
            val isLoading: Boolean,
            val isOnError: Boolean,
            val userInput: TextFieldValue,
        ) : State

        @Stable
        data object Empty : State
    }

    private val _isLoading = MutableStateFlow(false)
    private val _isOnError = MutableStateFlow(false)
    private val _userInput = MutableStateFlow(TextFieldValue())

    val state by lazy {
        combine(
            _isLoading,
            _isOnError,
            _userInput,
            State::Content
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = State.Empty
        )
    }

    fun onValueChanged(textFieldValue: TextFieldValue) {
        _userInput.update { textFieldValue }
    }

    fun dismissError() = _isOnError.update { false }

    fun onClick() {
        viewModelScope.launch {
            _isLoading.update { true }
            val userId = _userInput.value.text.toIntOrNull() ?: -1
            getUserUseCase(GetUserUseCase.Params(userId))
                .onSuccess { sendEvents(Event.Navigation.UserValidated(userId, it.email)) }
                .onFailure { _isOnError.update { true } }
            _isLoading.update { false }
        }
    }
}
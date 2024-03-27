package com.example.testapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.launch

interface ViewModelEvent

interface ViewModelEventDelegate<T : ViewModelEvent> {
    val event: SharedFlow<T>

    /**
     * Register listener which will be called when [event] will be subscribed
     * @param block function which will be called when [event] will be subscribed
     * */
    fun onEventSubscribed(block: () -> Unit)

    fun ViewModel.sendEvents(vararg events: T)
}

class ViewModelEventDelegateImpl<T : ViewModelEvent> : ViewModelEventDelegate<T> {

    private var onSubscribedAction: () -> Unit = {}
    private val _event = MutableSharedFlow<T>(extraBufferCapacity = 1)
    override val event: SharedFlow<T> = _event.asSharedFlow()
        .onSubscription { onSubscribedAction() }

    override fun onEventSubscribed(block: () -> Unit) {
        onSubscribedAction = block
    }

    override fun ViewModel.sendEvents(vararg events: T) {
        viewModelScope.launch { events.forEach { event -> _event.emit(event) } }
    }
}
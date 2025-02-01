package com.picpay.desafio.android.user.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.user.domain.FetchUserListUseCase
import com.picpay.desafio.android.user.presentation.intents.UserListIntents
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class UserViewModel(private val getUsersUseCase: FetchUserListUseCase) : ViewModel() {
    private val _usersState = MutableStateFlow<UserState>(UserState.Empty)
    val usersState: StateFlow<UserState> = _usersState

    private val intentChannel = Channel<UserListIntents>(Channel.UNLIMITED)

    init {
        processIntents()
    }

    private fun fetchUsers() {
        _usersState.value = UserState.Loading

        viewModelScope.launch {
            getUsersUseCase()
                .catch { cause -> _usersState.value = UserState.Error(cause.message ?: "") }
                .collect {
                    users -> _usersState.value = UserState.Success(users)
                }
        }
    }

    private fun processIntents() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect { intent ->
                when (intent) {
                    UserListIntents.LoadUsers -> fetchUsers()
                }
            }

        }
    }

    fun sendIntent(intent: UserListIntents) {
        viewModelScope.launch {
            intentChannel.send(intent)
        }
    }
}

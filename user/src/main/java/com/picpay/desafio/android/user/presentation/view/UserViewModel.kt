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
    private val _users = MutableStateFlow<UserState>(UserState.Empty)
    val usersState: StateFlow<UserState> = _users

    private val intentChannel = Channel<UserListIntents>(Channel.UNLIMITED)

    init {
        processIntents()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.value = UserState.Loading
            getUsersUseCase()
                .catch { cause -> _users.value = UserState.Error(cause.message ?: "") }
                .collect { users -> _users.value = UserState.Success(users) }
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
}

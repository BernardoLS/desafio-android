package com.picpay.desafio.android.user.presentation.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.user.domain.FetchUserListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class UserViewModel(private val getUsersUseCase: FetchUserListUseCase) : ViewModel() {
    private val _users = MutableStateFlow<UserState>(UserState.Empty)
    val users: StateFlow<UserState> = _users

    fun fetchUsers() {
        viewModelScope.launch {
            _users.value = UserState.Loading
            getUsersUseCase()
                .catch { cause -> _users.value = UserState.Error(cause.message ?: "") }
                .collect { users -> _users.value = UserState.Success(users) }
        }
    }
}
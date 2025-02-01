package com.picpay.desafio.android.user.presentation.view

import com.picpay.desafio.android.user.presentation.model.UserModel

interface UserState {
    object Loading : UserState
    data class Success(val users: List<UserModel>) : UserState
    data class Error(val message: String) : UserState
    object Empty : UserState
}
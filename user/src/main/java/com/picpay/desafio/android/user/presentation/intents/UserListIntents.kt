package com.picpay.desafio.android.user.presentation.intents

sealed class UserListIntents {
    data object LoadUsers : UserListIntents()
}
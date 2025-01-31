package com.picpay.desafio.android.user.domain

import com.picpay.desafio.android.user.presentation.model.UserModel

interface UserRepositoryInterface {
    suspend fun fetchRemoteUsers(): List<UserModel>
    suspend fun fetchLocalUsers(): List<UserModel>
    suspend fun insertUsers(users: List<UserModel>)
}
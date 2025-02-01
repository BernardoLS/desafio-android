package com.picpay.desafio.android.user.domain

import com.picpay.desafio.android.core.utils.ResultHandler
import com.picpay.desafio.android.user.data.remote.UserResponse
import com.picpay.desafio.android.user.presentation.model.UserModel

interface UserRepositoryInterface {
    suspend fun fetchRemoteUsers(): ResultHandler<List<UserResponse>>
    suspend fun fetchLocalUsers(): List<UserModel>
    suspend fun insertUsers(users: List<UserModel>)
}
package com.picpay.desafio.android.user.data.remote

import com.picpay.desafio.android.core.utils.ResultHandler
import com.picpay.desafio.android.core.utils.safeApiRequest

class UserRemoteDataSource(private val apiService: UserApiService) {
     suspend fun fetchUsers(): ResultHandler<List<UserResponse>> {
        return safeApiRequest { apiService.fetchUsers() }
    }
}
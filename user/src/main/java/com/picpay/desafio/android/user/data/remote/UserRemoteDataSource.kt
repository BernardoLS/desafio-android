package com.picpay.desafio.android.user.data.remote

class UserRemoteDataSource(private val apiService: UserApiService) {
     suspend fun getUsers(): List<UserResponse> {
        return apiService.getUsers()
    }
}
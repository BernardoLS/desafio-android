package com.picpay.desafio.android.user.data.remote

import retrofit2.http.GET

interface UserApiService {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}
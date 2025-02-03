package com.picpay.desafio.android.user.data.remote

import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.user.presentation.model.UserModel

data class UserResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("img") val img: String
)
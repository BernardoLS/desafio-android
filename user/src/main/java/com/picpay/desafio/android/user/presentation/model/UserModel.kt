package com.picpay.desafio.android.user.presentation.model

import android.os.Parcelable
import com.picpay.desafio.android.user.data.local.UserEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val id: Int,
    val name: String,
    val username: String,
    val img: String
) : Parcelable


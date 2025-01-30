package com.picpay.desafio.android.user.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val id: String,
    val name: String,
    val username: String,
    val img: String
) : Parcelable

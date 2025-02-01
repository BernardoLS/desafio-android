package com.picpay.desafio.android.user.data.mappers

import com.picpay.desafio.android.user.data.local.UserEntity
import com.picpay.desafio.android.user.data.remote.UserResponse
import com.picpay.desafio.android.user.presentation.model.UserModel

fun UserEntity.toModel() = UserModel(
    id = id,
    name = name,
    img = img,
    username = username
)

fun UserResponse.toModel() = UserModel(
    id = id,
    name = name,
    img = img,
    username = username
)

fun UserModel.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        username = username,
        img = img
    )
}

fun UserModel.toResponse(): UserResponse {
    return UserResponse(
        id = id,
        name = name,
        username = username,
        img = img
    )
}
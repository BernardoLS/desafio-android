package com.picpay.desafio.android.user.data.mappers

import com.picpay.desafio.android.user.data.local.UserEntity
import com.picpay.desafio.android.user.data.remote.UserResponse
import com.picpay.desafio.android.user.presentation.model.UserModel
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Test

class UserMappersTest {
    @Test
    fun `when call toEntity from UserModel should return UserEntity correctly`() {
        val userModel = UserModel(
            id = 1,
            img = "img",
            name = "name",
            username = "username"
        )
        val userEntity = UserEntity(
            id = 1,
            img = "img",
            name = "name",
            username = "username"
        )

        assertEquals(userEntity, userModel.toEntity())
    }

    @Test
    fun `when call toModel from UserEntity should return UserModel correctly`() {
        val userEntity = UserEntity(
            id = 1,
            img = "img",
            name = "name",
            username = "username"
        )
        val userModel = UserModel(
            id = 1,
            img = "img",
            name = "name",
            username = "username"
        )

        Assert.assertEquals(userModel, userEntity.toModel())
    }

    @Test
    fun `when call toModel from UserResponse should return UserModel correctly`() {
        val userEntity = UserResponse(
            id = 1,
            img = "img",
            name = "name",
            username = "username"
        )
        val userModel = UserModel(
            id = 1,
            img = "img",
            name = "name",
            username = "username"
        )

        Assert.assertEquals(userModel, userEntity.toModel())
    }
}
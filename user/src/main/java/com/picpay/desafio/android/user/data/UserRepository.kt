package com.picpay.desafio.android.user.data

import com.picpay.desafio.android.core.utils.ResultHandler
import com.picpay.desafio.android.user.data.local.UserLocalDataSource
import com.picpay.desafio.android.user.data.mappers.toEntity
import com.picpay.desafio.android.user.data.mappers.toModel
import com.picpay.desafio.android.user.data.remote.UserRemoteDataSource
import com.picpay.desafio.android.user.data.remote.UserResponse
import com.picpay.desafio.android.user.domain.UserRepositoryInterface
import com.picpay.desafio.android.user.presentation.model.UserModel

class UserRepository(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepositoryInterface {
    override suspend fun fetchRemoteUsers(): ResultHandler<List<UserResponse>> = remoteDataSource.fetchUsers()

    override suspend fun fetchLocalUsers(): List<UserModel> {
        return try {
            localDataSource.fetchUsers().map { it.toModel() }
        } catch (e: Exception) {
            arrayListOf()
        }
    }

    override suspend fun insertUsers(users: List<UserModel>) {
        val entities = users.map { it.toEntity() }
        localDataSource.insertUsers(entities)
    }
}
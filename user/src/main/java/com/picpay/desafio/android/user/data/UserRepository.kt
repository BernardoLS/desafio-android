package com.picpay.desafio.android.user.data

import com.picpay.desafio.android.user.data.local.UserLocalDataSource
import com.picpay.desafio.android.user.data.local.toModel
import com.picpay.desafio.android.user.data.remote.UserRemoteDataSource
import com.picpay.desafio.android.user.data.remote.toModel
import com.picpay.desafio.android.user.domain.UserRepositoryInterface
import com.picpay.desafio.android.user.presentation.model.UserModel

class UserRepository(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepositoryInterface {
    override suspend fun fetchRemoteUsers(): List<UserModel> = remoteDataSource.getUsers().map { it.toModel() }

    override suspend fun fetchLocalUsers(): List<UserModel> = localDataSource.getUsers().map { it.toModel() }

    override suspend fun insertUsers(users: List<UserModel>) {
        val entities = users.map { it.toEntity() }
        localDataSource.insertUsers(entities)
    }
}
package com.picpay.desafio.android.user.domain

import com.picpay.desafio.android.core.utils.ResultHandler
import com.picpay.desafio.android.core.utils.safeApiRequest
import com.picpay.desafio.android.user.presentation.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FetchUserListUseCase(private val repository: UserRepositoryInterface) {
    operator fun invoke(): Flow<List<UserModel>> = flow {
        try  {
            val localUsers = repository.fetchLocalUsers()
            emit(localUsers)

            val remoteUsersResponse = safeApiRequest { repository.fetchRemoteUsers() }
            when(remoteUsersResponse) {
                is ResultHandler.Error -> throw remoteUsersResponse.throwable
                is ResultHandler.Success -> {
                    val remoteUsers = remoteUsersResponse.data
                    if (localUsers != remoteUsers) {
                        repository.insertUsers(remoteUsers)
                        emit(remoteUsers)
                    }
                }
            }

        } catch(e: Exception) {
            throw e
        }
    }.flowOn(Dispatchers.IO)
}
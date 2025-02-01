package com.picpay.desafio.android.user.domain

import com.picpay.desafio.android.core.logger.AppLogger
import com.picpay.desafio.android.core.utils.ResultHandler
import com.picpay.desafio.android.core.utils.safeApiRequest
import com.picpay.desafio.android.user.data.mappers.toModel
import com.picpay.desafio.android.user.presentation.model.UserModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FetchUserListUseCase(
    private val repository: UserRepositoryInterface,
    private val logger: AppLogger,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    operator fun invoke(): Flow<List<UserModel>> = flow {
        try  {
            val localUsers = repository.fetchLocalUsers()
            emit(localUsers)

            when(val remoteUsersResponse = repository.fetchRemoteUsers()) {
                is ResultHandler.Error -> {
                    logger.logError(
                        remoteUsersResponse.throwable.message ?:  "Error fetching remote users",
                        remoteUsersResponse.throwable
                    )
                    throw remoteUsersResponse.throwable
                }
                is ResultHandler.Success -> {
                    val remoteUsers = remoteUsersResponse.data.map { it.toModel() }
                    if (localUsers != remoteUsers) {
                        repository.insertUsers(remoteUsers)
                        emit(remoteUsers)
                    }
                }
            }

        } catch(e: Exception) {
            logger.logError(e.message ?: "Error fetching users", e.cause)
        }
    }.flowOn(dispatcher)
}
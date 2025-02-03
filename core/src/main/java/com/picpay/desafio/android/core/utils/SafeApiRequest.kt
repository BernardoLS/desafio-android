package com.picpay.desafio.android.core.utils

suspend fun <T> safeApiRequest(request: suspend () -> T): ResultHandler<T> {
    return try {
        ResultHandler.Success(request())
    } catch (throwable: Throwable) {
        ResultHandler.Error(throwable)
    }
}
package com.picpay.desafio.android.core.utils

sealed class ResultHandler<out T> {
    data class Success<out T>(val data: T) : ResultHandler<T>()
    data class Error(val throwable: Throwable) : ResultHandler<Nothing>()
}
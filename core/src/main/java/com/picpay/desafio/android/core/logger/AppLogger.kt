package com.picpay.desafio.android.core.logger

import timber.log.Timber

abstract class AppLogger {

    fun init() {
        Timber.plant(getTree())
    }

    abstract fun getTree(): Timber.Tree

    fun logDebug(message: String) {
        Timber.d(message)
    }

    fun logError(message: String, throwable: Throwable? = null) {
        Timber.e(throwable, message)
    }

    fun logInfo(message: String) {
        Timber.i(message)
    }

    fun logWarning(message: String) {
        Timber.w(message)
    }

    fun logVerbose(message: String) {
        Timber.v(message)
    }

    fun logWtf(message: String) {
        Timber.wtf(message)
    }
}
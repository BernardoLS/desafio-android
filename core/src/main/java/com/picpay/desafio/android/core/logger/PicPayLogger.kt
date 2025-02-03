package com.picpay.desafio.android.core.logger

import timber.log.Timber


class PicPayLogger : AppLogger() {
    override fun getTree(): Timber.Tree {
        return Timber.DebugTree()
    }
}
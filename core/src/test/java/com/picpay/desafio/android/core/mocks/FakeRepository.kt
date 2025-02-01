package com.picpay.desafio.android.core.mocks

import com.picpay.desafio.android.core.utils.ResultHandler
import com.picpay.desafio.android.core.utils.safeApiRequest

class FakeRepository(private val fakeApi: FakeApiWithRequestInterface) {
    suspend fun doRequest() : ResultHandler<Unit> {
        return safeApiRequest {
            fakeApi.doRequest()
        }
    }
}
package com.skywalker.app.data.remoteDatasource

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class CallBacks<T>(call: Call<T>) : Callback<T> {
    private var call: Call<T>? = call
    private var retryCount = 0

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (!response.isSuccessful && retryCount++ < RETRIES) retry() else onFinalResponse(
            call,
            response,
            retryCount
        )
    }

    override fun onFailure(call: Call<T>, t: Throwable) {

    }

    private fun retry() {
        call?.clone()?.enqueue(this)
    }

    open fun onFinalResponse(call: Call<T>, response: Response<T>, numRetry: Int) {}
    open fun onFinalFailure(call: Call<T>, t: Throwable, numRetry: Int) {}

    companion object {

        const val RETRIES = 10

    }
}
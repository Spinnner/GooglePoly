package com.spinner.googlepolyproject.presentation.utils

data class ApiResult<out T>(val status: Status, val data: T? = null, val message: String? = "") {

    companion object {

        fun <T> success(data: T?): ApiResult<T> {
            return ApiResult(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String? = ""): ApiResult<T> {
            return ApiResult(Status.ERROR, null, msg)
        }

        fun <T> loading(): ApiResult<T> {
            return ApiResult(Status.LOADING)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

sealed class NetworkState
data class NetworkError(val message: String?) : NetworkState()
object Loading : NetworkState()
object Success : NetworkState()

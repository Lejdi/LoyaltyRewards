package com.futuremind.loyaltyrewards.data.utils

import com.futuremind.loyaltyrewards.R
import com.futuremind.loyaltyrewards.api.MockHttpException
import com.futuremind.loyaltyrewards.presentation.common.ErrorType
import java.net.SocketTimeoutException

suspend inline fun <reified ResponseModel> handleNetworkResponse(
    getResponse: () -> ResponseModel
): DataSourceResult<ResponseModel> {
    val response = try {
        getResponse()
    } catch (e: Exception) {
        return DataSourceResult.Error(handleException(e))
    }
    return DataSourceResult.Success(response)
}

fun handleException(e: Exception): ErrorType.DataSourceError {
    return when (e) {
        is SocketTimeoutException -> ErrorType.DataSourceError.Timeout
        is MockHttpException -> {
            val code = e.message?.substring(0, 3)?.toIntOrNull() //cannot access `code` field in class, but is displayed at the beginning of message
            when(code) {
                in 400 .. 499 -> {
                    ErrorType.DataSourceError.Other(e.message?.substring(5) ?: R.string.request_error)
                }
                in 500 .. 599 -> ErrorType.DataSourceError.ServerError
                else -> ErrorType.DataSourceError.Unknown
            }
        }
        else -> {
            ErrorType.DataSourceError.Unknown
        }
    }
}

package com.futuremind.loyaltyrewards.data.utils

import com.futuremind.loyaltyrewards.presentation.common.ErrorType
import java.lang.Exception
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
        else -> {
            ErrorType.DataSourceError.Unknown
        }
    }
}

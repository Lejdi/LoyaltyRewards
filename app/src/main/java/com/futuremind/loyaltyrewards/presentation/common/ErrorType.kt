package com.futuremind.loyaltyrewards.presentation.common

sealed class ErrorType(val details: String) {
    sealed class DataSourceError(networkErrorDetails: String) : ErrorType(networkErrorDetails) {
        data object Timeout : DataSourceError("Timeout")
        data object ServerError : DataSourceError("Server error")
        data object Unknown : DataSourceError("Unknown network error")
        data class Other(val message: String) : DataSourceError(message)
    }
    data object Unknown : ErrorType("Unknown error")
}

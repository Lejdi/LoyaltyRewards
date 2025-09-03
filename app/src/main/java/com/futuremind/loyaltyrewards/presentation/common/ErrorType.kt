package com.futuremind.loyaltyrewards.presentation.common

import com.futuremind.loyaltyrewards.R

sealed class ErrorType(val details: Int, val detailsArg: Any = "") {
    sealed class DataSourceError(networkErrorDetails: Int, detailsArg: Any = "") : ErrorType(networkErrorDetails, detailsArg) {
        data object Timeout : DataSourceError(R.string.timeout_error)
        data object ServerError : DataSourceError(R.string.server_error)
        data object Unknown : DataSourceError(R.string.unknown_network_error)
        data class Other(val message: Any) : DataSourceError(R.string.other_error_message, message)
    }
    data object Unknown : ErrorType(R.string.unknown_error)
}

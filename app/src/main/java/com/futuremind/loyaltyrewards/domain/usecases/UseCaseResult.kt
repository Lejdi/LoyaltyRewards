package com.futuremind.loyaltyrewards.domain.usecases

import com.futuremind.loyaltyrewards.presentation.common.ErrorType


sealed class UseCaseResult<DataType> {
    data class Success<DataType>(val data: DataType) : UseCaseResult<DataType>()
    data class Error<DataType>(val error: ErrorType) : UseCaseResult<DataType>()

    fun isFailure(): Boolean = this is Error
    fun getResultData(): DataType = (this as Success).data
    fun getErrorType(): ErrorType = (this as Error).error
}

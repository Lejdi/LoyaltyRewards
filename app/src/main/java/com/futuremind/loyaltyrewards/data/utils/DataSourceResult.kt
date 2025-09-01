package com.futuremind.loyaltyrewards.data.utils

import com.futuremind.loyaltyrewards.presentation.common.ErrorType

sealed class DataSourceResult<DataType> {
    data class Success<DataType>(val data: DataType) : DataSourceResult<DataType>()
    class Error<DataType>(val errorType: ErrorType.DataSourceError) : DataSourceResult<DataType>()
}

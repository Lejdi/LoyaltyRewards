package com.futuremind.loyaltyrewards.domain.usecases

import com.futuremind.loyaltyrewards.presentation.common.ErrorType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class UseCase<out Type, in Params> {
    abstract suspend fun execute(params: Params) : UseCaseResult<out Type>

    operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        executionDispatcher: CoroutineDispatcher = Dispatchers.IO,
        onResult: (UseCaseResult<out Type>) -> Unit = {}
    ){
        scope.launch {
            val result =
                withContext(executionDispatcher) {
                    runCatching { execute(params) }
                }
            if (result.isFailure) {
                onResult(
                    UseCaseResult.Error(ErrorType.Unknown)
                )
            } else {
                val useCaseResult = when (val resultData = result.getOrNull()) {
                    null -> {
                        UseCaseResult.Error(ErrorType.Unknown)
                    }
                    else -> {
                        resultData
                    }
                }
                onResult(useCaseResult)
            }
        }
    }
}
package com.futuremind.loyaltyrewards.domain.usecases.rewards

import com.futuremind.loyaltyrewards.data.repository.RewardsRepository
import com.futuremind.loyaltyrewards.data.utils.DataSourceResult
import com.futuremind.loyaltyrewards.domain.usecases.UseCase
import com.futuremind.loyaltyrewards.domain.usecases.UseCaseResult

class GetAvailablePoints(
    private val rewardsRepository: RewardsRepository
) : UseCase<Int, Unit>() {

    override suspend fun execute(params: Unit): UseCaseResult<Int> {
        val dataSourceResult = rewardsRepository.getAvailablePoints()
        return when(dataSourceResult){
            is DataSourceResult.Error -> UseCaseResult.Error(dataSourceResult.errorType)
            is DataSourceResult.Success -> UseCaseResult.Success(dataSourceResult.data.points)
        }
    }
}
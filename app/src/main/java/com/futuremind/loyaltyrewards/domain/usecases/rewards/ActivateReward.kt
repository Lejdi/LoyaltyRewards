package com.futuremind.loyaltyrewards.domain.usecases.rewards

import com.futuremind.loyaltyrewards.data.repository.RewardsRepository
import com.futuremind.loyaltyrewards.data.utils.DataSourceResult
import com.futuremind.loyaltyrewards.domain.usecases.UseCase
import com.futuremind.loyaltyrewards.domain.usecases.UseCaseResult

class ActivateReward(
    private val rewardsRepository: RewardsRepository
) : UseCase<Unit, Int>() {

    override suspend fun execute(rewardId: Int): UseCaseResult<Unit> {
        val dataSourceResult = rewardsRepository.changeRewardStatus(
            rewardId = rewardId,
            count = 1
        )
        return when(dataSourceResult){
            is DataSourceResult.Error -> UseCaseResult.Error(dataSourceResult.errorType)
            is DataSourceResult.Success -> UseCaseResult.Success(Unit)
        }
    }
}
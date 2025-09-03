package com.futuremind.loyaltyrewards.domain.usecases.rewards

import com.futuremind.loyaltyrewards.api.ApiReward
import com.futuremind.loyaltyrewards.api.ApiRewardActivationStatus
import com.futuremind.loyaltyrewards.data.repository.rewards.RewardsRepository
import com.futuremind.loyaltyrewards.data.utils.DataSourceResult
import com.futuremind.loyaltyrewards.domain.model.Reward
import com.futuremind.loyaltyrewards.domain.usecases.UseCase
import com.futuremind.loyaltyrewards.domain.usecases.UseCaseResult

class GetRewards(
    private val rewardsRepository: RewardsRepository
) : UseCase<List<Reward>, Unit>() {

    override suspend fun execute(params: Unit): UseCaseResult<List<Reward>> {
        val rewardsListDataSourceResult = rewardsRepository.getRewardsList()
        if(rewardsListDataSourceResult is DataSourceResult.Error){
            return UseCaseResult.Error(rewardsListDataSourceResult.errorType)
        }
        val rewardsActivationListDataSourceResult = rewardsRepository.getRewardsActivationList()
        if(rewardsActivationListDataSourceResult is DataSourceResult.Error){
            return UseCaseResult.Error(rewardsActivationListDataSourceResult.errorType)
        }

        val rewardsList = mapResponsesToDomainModel(
            (rewardsListDataSourceResult as DataSourceResult.Success).data,
            (rewardsActivationListDataSourceResult as DataSourceResult.Success).data,
        )
        return UseCaseResult.Success(rewardsList)
    }

    private fun mapResponsesToDomainModel(
        rewardsList: List<ApiReward>,
        rewardsActivationList: List<ApiRewardActivationStatus>
    ) : List<Reward> {
        val result = mutableListOf<Reward>()
        rewardsList.forEach { rewardsListItem ->
            val activated = rewardsActivationList.firstOrNull { it.id == rewardsListItem.id }
            val reward = Reward(
                id = rewardsListItem.id,
                name = rewardsListItem.name,
                coverUrl = rewardsListItem.coverUrl,
                pointsCost = rewardsListItem.pointsCost,
                activated = (activated?.activatedCount ?: 0) > 0
            )
            result.add(reward)
        }
        return result
    }
}
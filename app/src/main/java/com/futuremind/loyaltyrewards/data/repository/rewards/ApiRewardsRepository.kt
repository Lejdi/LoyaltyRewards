package com.futuremind.loyaltyrewards.data.repository.rewards

import com.futuremind.loyaltyrewards.api.RewardsApi
import com.futuremind.loyaltyrewards.data.utils.handleNetworkResponse

class ApiRewardsRepository(
    val api: RewardsApi
) : RewardsRepository {
    override suspend fun getAvailablePoints() = handleNetworkResponse {
        api.getPoints()
    }

    override suspend fun getRewardsList() = handleNetworkResponse {
        api.getRewards()
    }

    override suspend fun getRewardsActivationList() = handleNetworkResponse {
        api.getRewardsActivationStatus()
    }

    override suspend fun changeRewardStatus(rewardId: Int, count: Int) = handleNetworkResponse {
        api.changeRewardActivationStatus(
            rewardId = rewardId,
            activationsCount = count,
        )
    }
}